package org.sopt.and.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import org.sopt.and.R

sealed class BottomNavItem(
    val route: String,
    val title: Int,
    val icon: ImageVector
) {
    data object Home : BottomNavItem(
        route = "Home",
        title = R.string.home_content_desc,
        icon = Icons.Default.Home
    )

    data object Search : BottomNavItem(
        route = "Search",
        title = R.string.search_content_desc,
        icon = Icons.Default.Search
    )

    data object MY : BottomNavItem(
        route = "MY",
        title = R.string.my_content_desc,
        icon = Icons.Default.AccountCircle
    )
}