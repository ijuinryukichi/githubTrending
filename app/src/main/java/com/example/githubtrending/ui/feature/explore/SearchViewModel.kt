package com.example.githubtrending.ui.feature.explore

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubtrending.model.dto.github.SearchResponseDto
import com.example.githubtrending.repository.GitHubRepository
import com.example.githubtrending.ui.coroutines.ApiBaseState
import com.example.githubtrending.ui.coroutines.catching
import com.example.githubtrending.ui.paging.PagingExecutor
import com.example.githubtrending.ui.paging.PagingViewModel
import com.example.githubtrending.ui.stable.StableOnClickItemInvoker
import com.example.githubtrending.ui.stable.StableSendViewableLogInvoker
import com.example.githubtrending.ui.stable.StableTransitionEventEmitter
import com.example.githubtrending.ui.transition.TransitionEventInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val gitHubRepository: GitHubRepository,
    val apiBaseState: ApiBaseState,
) : ViewModel(), PagingViewModel {

    companion object {

        private const val SEARCH_QUERY = "searchQuery"
    }

    sealed interface TransitionEvent : TransitionEventInterface {
        data class OnClickItem(val url: String) : TransitionEvent
    }

    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY, initialValue = "")

    private val _navigationChannel = Channel<TransitionEventInterface>()
    val navigationChannel = _navigationChannel.receiveAsFlow()

    private val paging = PagingExecutor<SearchUiState>()

    override val nextCursor: MutableStateFlow<String?> = MutableStateFlow("1")
    override val refreshFlow = MutableStateFlow(false)
    override val scrollPosition = MutableStateFlow<Int?>(null)

    val uiState: StateFlow<SearchUiState?> =
        combine(searchQuery.filter { it.isNotBlank() }, refreshFlow, nextCursor) { query, refresh, cursor ->
            if (refresh) {
                null
            } else {
                fetch(query, cursor?.toIntOrNull() ?: 1).first()
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun onSearchQuery(query: String) {
        paging.reset(this) {
            savedStateHandle[SEARCH_QUERY] = query
        }
    }

    private fun fetch(query: String, page: Int): Flow<SearchUiState?> = paging.execute(
        viewModel = this,
        apiBaseState = apiBaseState,
        setNextCursor = { result ->
            // apiの戻り値にnextCursorがある場合は不要な処理
            if (result.totalCount >= (uiState.value?.items?.size ?: 1)) {
                nextCursor.value = (nextCursor.value?.toIntOrNull() ?: 1).plus(1).toString()
            } else {
                nextCursor.value = null
            }
        },
        fetchList = {
            gitHubRepository.fetch(query, page)
        },
        createItemUiState = { result: SearchResponseDto, loadPageInvoker, _ ->
            SearchUiState(
                result.totalCount,
                result.isIncompleteResults,
                result.items.map {
                    val itemViewModel =
                        RepositoriesItemViewModel(
                            StableTransitionEventEmitter(_navigationChannel::trySend)
                        )
                    val itemUiState = RepositoriesItemUiState(
                        it,
                        StableOnClickItemInvoker(itemViewModel::onClickItem)
                    )
                    itemViewModel.init(itemUiState)
                    itemUiState
                },
                StableSendViewableLogInvoker { index ->
                    // 画面表示ログを送信
                },
                loadPageInvoker,
            )
        },
        onPostSuccess = { isLastPage, currentPage ->
            println("currentPage = ${currentPage}")
            println("isLastPage = ${isLastPage}")
        }
    ).catching(apiBaseState)

}