package org.sopt.and.presentation.signIn

sealed class SignInIntent {
    data class EnterUsername(val username: String) : SignInIntent()
    data class EnterPassword(val password: String) : SignInIntent()
    object TogglePasswordVisibility : SignInIntent()
    object SubmitSignIn : SignInIntent()
}