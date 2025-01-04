package com.example.githubtrending.ui.transition

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.githubtrending.ui.feature.developers.DevelopersScreen
import com.example.githubtrending.ui.feature.explore.SearchScreen
import com.example.githubtrending.ui.feature.explore.WebViewScreen
import com.example.githubtrending.ui.feature.trending.TrendingScreen

const val webViewScreenRouteParameterName = "url"
const val webViewScreenRoute = "webPage/{$webViewScreenRouteParameterName}"

@Composable
fun GithubTrendingNavHost() {
    val navHostController = rememberNavController()
    val myNavController = remember(key1 = navHostController) {
        GithubTrendingNavController(navHostController)
    }

    val onNavigateToRoute = myNavController::navigateToBottomBarRoute

    NavHost(
        navController = myNavController.navController,
        startDestination = "top"
    ) {
        navigation(
            route = "top",
            startDestination = BottomTabType.TRENDING.route
        ) {
            composable(BottomTabType.TRENDING.route) { from ->
                TrendingScreen(onNavigateToRoute = onNavigateToRoute) { url ->
                    myNavController.navigate(url, from)
                }
            }
            composable(BottomTabType.DEVELOPERS.route) { from ->
                DevelopersScreen(onNavigateToRoute = onNavigateToRoute) { url ->
                    myNavController.navigate(url, from)
                }
            }
            composable(BottomTabType.EXPLORE.route) { from ->
                SearchScreen(onNavigateToRoute = onNavigateToRoute) { url ->
                    myNavController.navigate(url, from)
                }
            }
        }
        composable(
            webViewScreenRoute,
            arguments = listOf(
                navArgument(webViewScreenRouteParameterName) { type = NavType.StringType },
            ),
        ) { backStackEntry ->
            // 勝手にviewModelのSavedStateHandleに設定されるのでここでやる必要なし
//            val url =
//                URLDecoder.decode(backStackEntry.arguments?.getString(webViewScreenRouteParameterName) ?: "", "UTF-8")
            WebViewScreen()
        }
    }
}
