package org.sopt.and.data.di

import org.sopt.and.data.model.request.RequestLoginData
import org.sopt.and.data.model.request.RequestUserRegistrationData
import org.sopt.and.data.model.request.ResponseLogin
import org.sopt.and.data.model.request.ResponseUserRegistration
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface UserRegistrationService {
    @POST("/user")
    suspend fun postUserRegistration(
        @Body userRequest: RequestUserRegistrationData
    ): Response<ResponseUserRegistration>
}

interface LoginService {
    @POST("/login")
    suspend fun postLogin(
        @Body loginRequeset: RequestLoginData
    ): Response<ResponseLogin>
}



