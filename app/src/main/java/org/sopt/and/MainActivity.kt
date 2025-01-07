package org.sopt.and

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import org.sopt.and.navigation.AuthNavItem
import org.sopt.and.presentation.signIn.SignInScreen
import org.sopt.and.presentation.signup.SignUpScreen
import org.sopt.and.presentation.ui.main.MainScreen
import org.sopt.and.ui.theme.MEMENTOTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MEMENTOTheme {
                MyAppScreen()
            }
        }
    }
}

@HiltAndroidApp
class MyApp : Application()

@Composable
fun MyAppScreen() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = AuthNavItem.SignUp.route) {
        composable(AuthNavItem.SignUp.route) {
            SignUpScreen(navController = navController)
        }
        composable(AuthNavItem.SignIn.route) {
            SignInScreen(navController = navController)
        }
        composable(AuthNavItem.Main.route) {
            MainScreen()
        }
    }
}
