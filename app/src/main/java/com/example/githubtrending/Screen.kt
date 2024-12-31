package com.example.githubtrending

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Trending : Screen("trending", "Trending", Icons.Filled.ThumbUp)
    object Developers : Screen("developers", "Developers", Icons.Filled.Person)
    object Explore : Screen("explore", "Explore", Icons.Filled.Search)
}