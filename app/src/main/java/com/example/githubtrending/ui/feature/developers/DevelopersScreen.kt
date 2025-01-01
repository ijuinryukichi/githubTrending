package com.example.githubtrending.ui.feature.developers

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.githubtrending.ui.feature.explore.SearchViewModel

@Composable
fun DevelopersScreen(
    viewModel: DevelopersViewModel = hiltViewModel(),
    onNavigateToRoute: (String) -> Unit,
    navigateToNextPage: (String) -> Unit
) {
    // UI implementation
}