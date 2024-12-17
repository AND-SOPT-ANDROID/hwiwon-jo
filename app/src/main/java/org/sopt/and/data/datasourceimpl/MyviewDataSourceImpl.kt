package org.sopt.and.data.datasourceimpl

import org.sopt.and.data.datasource.MyviewDataSource
import org.sopt.and.data.model.response.MyviewResponseDto
import org.sopt.and.data.service.HobbyService
import retrofit2.Response
import javax.inject.Inject

class MyviewDataSourceImpl @Inject constructor(
    private val hobbyService: HobbyService
) : MyviewDataSource {
    override suspend fun getHobby(token: String): Response<MyviewResponseDto> {
        return hobbyService.getHobby(token)
    }
}