package org.sopt.and.data.service

import org.sopt.and.data.model.request.LoginRequestDto
import org.sopt.and.data.model.request.UserRequestDto
import org.sopt.and.data.model.response.LoginResponseDto
import org.sopt.and.data.model.response.UserResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/user")
    suspend fun postUserRegistration(
        @Body userRequest: UserRequestDto
    ): Response<UserResponseDto>

    @POST("/login")
    suspend fun postLogin(
        @Body loginRequest: LoginRequestDto
    ): Response<LoginResponseDto>
}



