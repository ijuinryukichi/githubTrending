package com.example.githubtrending.ui.feature.trending

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TrendingScreen(
    viewModel: TrendingViewModel = hiltViewModel(),
    onNavigateToRoute: (String) -> Unit,
    navigateToNextPage: (String) -> Unit
) {
    // UI implementation
}