package com.example.githubtrending.ui.feature.explore

import androidx.compose.runtime.Stable
import com.example.githubtrending.model.dto.github.RepositoryDto
import com.example.githubtrending.ui.CollectionItemUiState
import com.example.githubtrending.ui.stable.StableOnClickItemInvoker

@Stable
data class RepositoriesItemUiState(
    val repositoryDto: RepositoryDto,
    private val onClickItemInvoker: StableOnClickItemInvoker
) : CollectionItemUiState {

    val fullName = repositoryDto.fullName
    val description = repositoryDto.description
    val language = repositoryDto.language
    val stars = repositoryDto.stars
    val forks = repositoryDto.forks
    val avatarUrl = repositoryDto.avatarUrl

    fun onClickItem() {
        onClickItemInvoker.invoke()
    }
}