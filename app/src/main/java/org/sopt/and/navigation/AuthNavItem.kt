package org.sopt.and.navigation


sealed class AuthNavItem(val route: String) {
    object SignUp : AuthNavItem("signUp")
    object SignIn : AuthNavItem("signIn")

    object Main : AuthNavItem("main")
}