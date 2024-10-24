package org.sopt.and.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SignInViewModel(
    private val signUpViewModel: SignUpViewModel
) : ViewModel() {

    var emailLogin by mutableStateOf("")
    var passwordLogin by mutableStateOf("")

    var emailError by mutableStateOf("")
    var passwordError by mutableStateOf("")

    fun validate() {
        emailError = if (emailLogin.isEmpty()) "이메일을 입력하세요." else ""
        passwordError = if (passwordLogin.isEmpty()) "비밀번호를 입력하세요." else ""
    }

    fun validateSignIn(email: String, password: String): Boolean {
        return email == signUpViewModel.email && password == signUpViewModel.password
    }

    fun setEmailAndPassword(email: String, password: String) {
        this.emailLogin = email
        this.passwordLogin = password
    }
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