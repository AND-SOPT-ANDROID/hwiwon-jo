package org.sopt.and.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    @SerialName("username") val userName: String,
    @SerialName("password") val password: String,
)