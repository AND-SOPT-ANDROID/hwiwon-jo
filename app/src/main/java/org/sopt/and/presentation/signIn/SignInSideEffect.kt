package org.sopt.and.presentation.signIn

sealed class SignInSideEffect {
    data class ShowToast(val message: String) : SignInSideEffect()
    object NavigateToMain : SignInSideEffect()
}