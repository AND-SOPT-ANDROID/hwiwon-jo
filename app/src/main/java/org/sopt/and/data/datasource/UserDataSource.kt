package org.sopt.and.data.datasource

import org.sopt.and.data.model.request.UserRequestDto

import org.sopt.and.data.model.response.UserResponseDto

interface UserDataSource {
    suspend fun postUserRegistration(request: UserRequestDto): Result<UserResponseDto>
}