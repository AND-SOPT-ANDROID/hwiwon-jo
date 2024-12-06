package org.sopt.and.data.datasource

import org.sopt.and.data.model.request.LoginRequestDto
import org.sopt.and.data.model.response.LoginResponseDto

interface LoginDataSource {
    suspend fun postLogin(requestData: LoginRequestDto): Result<LoginResponseDto>
}