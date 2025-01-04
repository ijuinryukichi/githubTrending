package com.example.githubtrending.ui.feature.trending

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.githubtrending.ui.transition.ApiErrorLaunchedEffect
import com.example.githubtrending.ui.transition.BottomTabType
import com.example.githubtrending.ui.transition.GithubTrendingNavigationBar
import com.example.githubtrending.ui.transition.TransitionEventLaunchedEffect
import com.example.githubtrending.ui.transition.webViewScreenRoute
import com.example.githubtrending.ui.transition.webViewScreenRouteParameterName
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun TrendingScreen(
    viewModel: TrendingViewModel = hiltViewModel(),
    onNavigateToRoute: (String) -> Unit,
    navigateToNextPage: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ApiErrorLaunchedEffect(viewModel.apiBaseState.error)
    TransitionEventLaunchedEffect(viewModel.navigationChannel) { event ->
        when (event) {
            is TrendingViewModel.TransitionEvent.OnClickItem -> {
                val encodedUrl = URLEncoder.encode(event.url, StandardCharsets.UTF_8.toString())
                navigateToNextPage(webViewScreenRoute.replace("{$webViewScreenRouteParameterName}", encodedUrl))
            }
        }
    }

    Scaffold(
        bottomBar = {
            GithubTrendingNavigationBar(
                BottomTabType.entries,
                BottomTabType.TRENDING.route,
                onNavigateToRoute,
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize(), contentAlignment = Alignment.Center) {
            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else {
                DoForceRepositoriesScreen(uiState, viewModel::onClickItem)
            }
        }
    }
}