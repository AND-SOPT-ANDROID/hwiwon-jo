package org.sopt.and.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestUserRegistration(
    @SerialName("username")
    val userName: String,
    @SerialName("password")
    val password: String,
    @SerialName("hobby")
    val hobby: String
)

@Serializable
data class ResponseUserRegistration(
    @SerialName("result")
    val result: ResultUserNo?
)

@Serializable
data class ResultUserNo(
    @SerialName("no")
    val no: Int
)