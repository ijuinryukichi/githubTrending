package com.example.githubtrending.ui.feature.explore

import androidx.compose.runtime.Stable
import com.example.githubtrending.ui.CollectionItemUiState
import com.example.githubtrending.ui.paging.PagingUiState
import com.example.githubtrending.ui.stable.StableReachedLoadPageInvoker
import com.example.githubtrending.ui.stable.StableSendViewableLogInvoker

@Stable
data class SearchUiState(
    val totalCount: Int,
    val isIncompleteResults: Boolean,
    override val items: List<CollectionItemUiState>,
    private val sendViewableLogInvoker: StableSendViewableLogInvoker,
    override val reachedLoadPageInvoker: StableReachedLoadPageInvoker,
) : PagingUiState {

    fun sendViewableLog(index: Int) {
        sendViewableLogInvoker.invoke(index)
    }

    override fun copyItems(items: List<CollectionItemUiState>): SearchUiState {
        return copy(items = items)
    }
}