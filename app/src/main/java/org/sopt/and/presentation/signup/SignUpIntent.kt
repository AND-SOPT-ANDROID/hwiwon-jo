package org.sopt.and.presentation.signup

sealed class SignUpIntent {
    data class EnterUsername(val username: String) : SignUpIntent()
    data class EnterPassword(val password: String) : SignUpIntent()
    data class EnterHobby(val hobby: String) : SignUpIntent()
    object TogglePasswordVisibility : SignUpIntent()
    object SubmitSignUp : SignUpIntent()
}
