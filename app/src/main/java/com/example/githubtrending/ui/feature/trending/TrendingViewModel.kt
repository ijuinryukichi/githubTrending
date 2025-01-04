package com.example.githubtrending.ui.feature.trending

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubtrending.repository.DoForceRepository
import com.example.githubtrending.ui.coroutines.ApiBaseState
import com.example.githubtrending.ui.coroutines.catching
import com.example.githubtrending.ui.transition.TransitionEventInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val doForceRepository: DoForceRepository,
    val apiBaseState: ApiBaseState,
) : ViewModel() {

    sealed interface TransitionEvent : TransitionEventInterface {
        data class OnClickItem(val url: String) : TransitionEvent
    }

    private val _navigationChannel = Channel<TransitionEventInterface>()
    val navigationChannel = _navigationChannel.receiveAsFlow()

    private val _uiState = MutableStateFlow(TrendingUiState())
    val uiState: StateFlow<TrendingUiState> = _uiState.asStateFlow()

    init {
        fetchTrendingRepositories()
    }

    private fun fetchTrendingRepositories() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        doForceRepository.fetchTrendingRepositories()
            .onEach { repositories ->
                _uiState.value = TrendingUiState(repositories = repositories, isLoading = false)
            }
            .catching(apiBaseState)
            .launchIn(viewModelScope)
    }

    fun onClickItem(url: String) {
        _navigationChannel.trySend(TransitionEvent.OnClickItem(url))
    }

}