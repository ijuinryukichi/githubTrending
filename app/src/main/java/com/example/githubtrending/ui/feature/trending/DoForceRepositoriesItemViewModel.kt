package com.example.githubtrending.ui.feature.trending

import com.example.githubtrending.ui.stable.StableTransitionEventEmitter

class DoForceRepositoriesItemViewModel(
    private val emitter: StableTransitionEventEmitter,
) {

    private lateinit var repositoriesItemUiState: DoForceRepositoriesItemUiState

    fun init(repositoriesItemUiState: DoForceRepositoriesItemUiState) {
        this.repositoriesItemUiState = repositoriesItemUiState
    }

    fun onClickItem() {
        emitter.trySend(TrendingViewModel.TransitionEvent.OnClickItem(repositoriesItemUiState.repositoryDto.repo))
    }
}