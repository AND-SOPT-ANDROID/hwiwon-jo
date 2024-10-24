package org.sopt.and.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.and.navigation.NavRoutes
import org.sopt.and.presentation.ui.auth.MyviewScreen
import org.sopt.and.presentation.ui.auth.SignInScreen
import org.sopt.and.presentation.ui.auth.SignUpScreen
import org.sopt.and.presentation.viewmodel.SignUpViewModel
import org.sopt.and.ui.theme.ANDANDROIDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                val signUpViewModel: SignUpViewModel = viewModel()
                MyApp(signUpViewModel)
            }
        }
    }
}

@Composable
fun MyApp(signUpViewModel: SignUpViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = NavRoutes.SignUp.route) {
        composable(NavRoutes.SignUp.route) {
            SignUpScreen(signUpViewModel = signUpViewModel, navController = navController)
        }

        composable(NavRoutes.SignIn.route) {
            SignInScreen(signUpViewModel = signUpViewModel, navController = navController)
        }

        composable("myView") {
            MyviewScreen(signUpViewModel = signUpViewModel, navController = navController)
        }
    }

}
