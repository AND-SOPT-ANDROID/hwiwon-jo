package org.sopt.and.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SignInViewModel(
    private val signUpViewModel: SignUpViewModel
) : ViewModel() {

    private var _emailLogin by mutableStateOf("")
    val emailLogin: String
        get() = _emailLogin

    private var _passwordLogin by mutableStateOf("")
    val passwordLogin: String
        get() = _passwordLogin

    var emailError by mutableStateOf("")
    var passwordError by mutableStateOf("")

    fun updateEmailLogin(newEmail: String) {
        _emailLogin = newEmail
        validateEmail()
    }

    fun updatePasswordLogin(newPassword: String) {
        _passwordLogin = newPassword
        validatePassword()
    }

    fun validateEmail() {
        emailError = if (emailLogin.isEmpty()) "이메일을 입력하세요." else ""
    }

    fun validatePassword() {
        passwordError = if (passwordLogin.isEmpty()) "비밀번호를 입력하세요." else ""
    }

    fun validateSignIn(): Boolean =
        emailLogin == signUpViewModel.username && passwordLogin == signUpViewModel.password

}

class SignInViewModelFactory(private val signUpViewmodel: SignUpViewModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(signUpViewmodel) as T
        }
        throw IllegalArgumentException("알 수 없는 뷰 모델 클래스")
    }
}