package org.sopt.and.presentation.signup

sealed class SignUpSideEffect {
    data class ShowToast(val message: String) : SignUpSideEffect()
    object NavigateToSignIn : SignUpSideEffect()
}
