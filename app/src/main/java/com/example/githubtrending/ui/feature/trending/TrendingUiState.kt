package com.example.githubtrending.ui.feature.trending

import androidx.compose.runtime.Stable
import com.example.githubtrending.model.dto.doforce.DoForceRepositoryDto

data class TrendingUiState(
    val repositories: List<DoForceRepositoryDto> = emptyList(),
    val isLoading: Boolean = false
)