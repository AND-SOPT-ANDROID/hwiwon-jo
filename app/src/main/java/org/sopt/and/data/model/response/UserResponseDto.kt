package org.sopt.and.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(
    @SerialName("result")
    val result: ResultUserNo
)

@Serializable
data class ResultUserNo(
    @SerialName("no")
    val no: Int
)