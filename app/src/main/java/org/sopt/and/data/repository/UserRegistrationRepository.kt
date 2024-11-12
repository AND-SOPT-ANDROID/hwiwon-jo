package org.sopt.and.data.repository

import org.sopt.and.data.api.UserRegistrationService
import org.sopt.and.data.dto.RequestUserRegistration
import org.sopt.and.data.dto.ResponseUserRegistration
import javax.inject.Inject

class UserRegistrationRepository @Inject constructor(
    private val apiService: UserRegistrationService
) {
    suspend fun registerUser(userRequest: RequestUserRegistration): Result<ResponseUserRegistration> {
        return try {
            val response = apiService.registerUser(userRequest)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                val errorCode = response.errorBody()?.string() ?: "알수없슴"
                Result.failure(Exception("Error code : ${response.code()}, 에러 코드 : $errorCode"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}