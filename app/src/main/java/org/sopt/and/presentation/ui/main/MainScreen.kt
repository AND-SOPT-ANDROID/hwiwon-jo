package org.sopt.and.presentation.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.sopt.and.navigation.BottomNavItem
import org.sopt.and.presentation.viewmodel.SignUpViewModel

@Composable
fun MainScreen(signUpViewModel: SignUpViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Box(Modifier.padding(it)) {
            BottomNavGraph(navController = navController, signUpViewModel = signUpViewModel)
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf<BottomNavItem>(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.MY
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = Color.DarkGray
    ) {
        screens.forEach { screens ->
            AddItem(
                item = screens,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    item: BottomNavItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = { Text(text = stringResource(item.title), fontSize = 10.sp) },
        icon = {
            Icon(
                imageVector = item.icon,
                contentDescription = stringResource(item.title)
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == item.route
        } == true,
        selectedContentColor = Color.White,
        unselectedContentColor = Color.Gray,
        onClick = {
            navController.navigate(item.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
            }
        }
    )
}
