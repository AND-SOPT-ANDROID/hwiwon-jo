package org.sopt.and.presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.and.navigation.BottomNavItem
import org.sopt.and.presentation.viewmodel.SignUpViewModel

@Composable
fun BottomNavGraph(navController: NavHostController, signUpViewModel: SignUpViewModel) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(route = BottomNavItem.Home.route) {
            HomeScreen()
        }
        composable(route = BottomNavItem.Search.route) {
            SearchScreen()
        }
        composable(route = BottomNavItem.MY.route) {
            MyviewScreen(signUpViewModel = signUpViewModel)
        }

    }
}