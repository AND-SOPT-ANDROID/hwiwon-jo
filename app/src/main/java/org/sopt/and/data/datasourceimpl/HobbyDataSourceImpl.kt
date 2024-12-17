package org.sopt.and.data.datasourceimpl

import org.sopt.and.data.datasource.HobbyDataSource
import org.sopt.and.data.model.response.HobbyResponseDto
import org.sopt.and.data.service.HobbyService
import retrofit2.Response
import javax.inject.Inject

class HobbyDataSourceImpl @Inject constructor(
    private val hobbyService: HobbyService
) : HobbyDataSource {
    override suspend fun getHobby(token: String): Response<HobbyResponseDto> {
        return hobbyService.getHobby(token)
    }
}