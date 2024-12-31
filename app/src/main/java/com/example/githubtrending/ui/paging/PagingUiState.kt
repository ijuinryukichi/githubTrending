package com.example.githubtrending.ui.paging

import com.example.githubtrending.ui.CollectionItemUiState
import com.example.githubtrending.ui.stable.StableReachedLoadPageInvoker

interface PagingUiState {

    val items: List<CollectionItemUiState>
    val reachedLoadPageInvoker: StableReachedLoadPageInvoker

    fun reachedPageLoadPosition() {
        reachedLoadPageInvoker.invoke()
    }

    fun copyItems(items: List<CollectionItemUiState>): PagingUiState
}