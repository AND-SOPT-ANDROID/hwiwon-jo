package org.sopt.and.presentation.utils

object RegexConstants {
    //회원가입 정규 표현식
    val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    val LOWER_CASE_REGEX = Regex("[a-z]")
    val UPPER_CASE_REGEX = Regex("[A-Z]")
    val DIGIT_REGEX = Regex("[0-9]")
    val SPECIAL_REGEX = Regex("[!@#\$%^&*(),.?\\\":{}|<>]")
}