package org.sopt.and.data.api

import org.sopt.and.data.dto.RequestUserRegistration
import org.sopt.and.data.dto.ResponseUserRegistration
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface UserRegistrationService {
    @POST("/user")
    suspend fun registerUser(
        @Body userRequest: RequestUserRegistration
    ): Response<ResponseUserRegistration>
}