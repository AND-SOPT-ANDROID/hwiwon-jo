package org.sopt.and.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    @SerialName("result")
    val result: ResultToken
)

@Serializable
data class ResultToken(
    @SerialName("token")
    val token: String
)