package org.sopt.and.presentation.signup

data class SignUpState(
    val email: String = "",
    val password: String = "",
    val hobby: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)
