package org.sopt.and.data.datasource

import org.sopt.and.data.model.response.HobbyResponseDto

interface HobbyDataSource {
    suspend fun getHobby(token: String): retrofit2.Response<HobbyResponseDto>
}