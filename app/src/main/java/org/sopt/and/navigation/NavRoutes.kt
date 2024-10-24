package org.sopt.and.navigation


sealed class NavRoutes(val route: String) {
    object SignUp : NavRoutes("signUp")
    object SignIn : NavRoutes("signIn")
    data class MyView(val email: String) : NavRoutes("myView/$email")
}