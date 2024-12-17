package org.sopt.and.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyviewResponseDto(
    @SerialName("result")
    val result: ResponseMyHobbyDataResult
)

@Serializable
data class ResponseMyHobbyDataResult(
    @SerialName("hobby")
    val hobby: String
)