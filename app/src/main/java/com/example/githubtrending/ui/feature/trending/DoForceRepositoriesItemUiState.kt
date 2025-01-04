package com.example.githubtrending.ui.feature.trending

import androidx.compose.runtime.Stable
import com.example.githubtrending.model.dto.doforce.DoForceRepositoryDto
import com.example.githubtrending.ui.CollectionItemUiState
import com.example.githubtrending.ui.stable.StableOnClickItemInvoker

@Stable
data class DoForceRepositoriesItemUiState(
    val repositoryDto: DoForceRepositoryDto,
    private val onClickItemInvoker: StableOnClickItemInvoker
) : CollectionItemUiState {

    val fullName = repositoryDto.repo
    val description = repositoryDto.desc
    val language = repositoryDto.lang
    val stars = repositoryDto.stars
    val forks = repositoryDto.forks
    val avatarUrl = repositoryDto.buildBy.firstOrNull()?.avatar ?: ""

    fun onClickItem() {
        onClickItemInvoker.invoke()
    }
}