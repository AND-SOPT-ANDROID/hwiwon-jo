package org.sopt.and.data.datasource

import org.sopt.and.data.model.response.MyviewResponseDto

interface MyviewDataSource {
    suspend fun getHobby(token: String): retrofit2.Response<MyviewResponseDto>
}