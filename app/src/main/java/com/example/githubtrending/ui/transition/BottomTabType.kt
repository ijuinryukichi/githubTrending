package com.example.githubtrending.ui.transition

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.githubtrending.R

enum class BottomTabType(
    @StringRes val titleResId: Int,
    val icon: ImageVector,
    val route: String
) {

    TRENDING(R.string.trending, Icons.Outlined.Search, "top/trending"),
    DEVELOPERS(R.string.developers, Icons.AutoMirrored.Outlined.List, "top/developers"),
    EXPLORE(R.string.explore, Icons.Outlined.AccountCircle, "top/explore")
}