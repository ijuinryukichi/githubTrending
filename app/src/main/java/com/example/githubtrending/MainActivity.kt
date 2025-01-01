package com.example.githubtrending

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.githubtrending.ui.feature.developers.DevelopersScreen
import com.example.githubtrending.ui.feature.explore.ExploreScreen
import com.example.githubtrending.ui.feature.trending.TrendingScreen
import com.example.githubtrending.ui.theme.GithubTrendingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GithubTrendingTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController) },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavigationHost(navController, Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        Screen.Trending,
        Screen.Developers,
        Screen.Explore
    )
    NavigationBar {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = null) },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = Screen.Trending.route, modifier = modifier) {
        composable(Screen.Trending.route) { TrendingScreen() }
        composable(Screen.Developers.route) { DevelopersScreen() }
        composable(Screen.Explore.route) { ExploreScreen() }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    return navBackStackEntry?.destination?.route
}