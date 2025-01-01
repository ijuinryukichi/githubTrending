package com.example.githubtrending.ui.feature.explore

import com.example.githubtrending.ui.stable.StableTransitionEventEmitter

class RepositoriesItemViewModel(
    private val emitter: StableTransitionEventEmitter,
) {

    private lateinit var repositoriesItemUiState: RepositoriesItemUiState

    fun init(repositoriesItemUiState: RepositoriesItemUiState) {
        this.repositoriesItemUiState = repositoriesItemUiState
    }

    fun onClickItem() {
        emitter.trySend(SearchViewModel.TransitionEvent.OnClickItem(repositoriesItemUiState.repositoryDto.url))
    }
}