package org.sopt.and.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.sopt.and.presentation.utils.Constants
import org.sopt.and.presentation.utils.RegexConstants

class SignUpViewModel : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isPasswordVisible by mutableStateOf(false)


    fun validateSignUp(email: String, password: String): Boolean {
        if (!RegexConstants.EMAIL_REGEX.matches(email)) {
            return false
        }
        if (password.length > Constants.MAX_PASSWORD_LENGTH || password.length < Constants.MIN_PASSWORD_LENGTH) {
            return false
        }
        val hasLowerCase = RegexConstants.LOWER_CASE_REGEX.containsMatchIn(password)
        val hasUpperCase = RegexConstants.UPPER_CASE_REGEX.containsMatchIn(password)
        val hasDigitCase = RegexConstants.DIGIT_REGEX.containsMatchIn(password)
        val hasSpecialChar = RegexConstants.SPECIAL_REGEX.containsMatchIn(password)

        val criteriaCnt =
            listOf(hasLowerCase, hasUpperCase, hasDigitCase, hasSpecialChar).count { it }

        return criteriaCnt >= Constants.PASSWORD_CRITERIA_COUNT
    }

    fun setEmailAndPassword(email: String, password: String) {
        this.email = email
        this.password = password
    }

}