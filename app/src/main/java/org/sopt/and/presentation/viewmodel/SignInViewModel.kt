package org.sopt.and.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignInViewModel(
    private val signUpViewModel: SignUpViewModel
) : ViewModel() {

    var emailLogin by mutableStateOf("")
    var passwordLogin by mutableStateOf("")

    fun validateSignIn(emailLogin: String, passwordLogin: String): Boolean {
        return emailLogin == signUpViewModel.email && passwordLogin == signUpViewModel.password
    }

    fun setEmailAndPassword(email: String, password: String) {
        this.emailLogin = email
        this.passwordLogin = password
    }
}