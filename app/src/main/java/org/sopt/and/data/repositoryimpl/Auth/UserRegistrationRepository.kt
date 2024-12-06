package org.sopt.and.data.repositoryimpl.Auth

import org.sopt.and.data.di.UserRegistrationService
import org.sopt.and.data.model.request.RequestUserRegistrationData
import org.sopt.and.data.model.request.ResponseUserRegistration
import javax.inject.Inject

class UserRegistrationRepository @Inject constructor(
    private val apiService: UserRegistrationService
) {
    suspend fun postUserRegistration(requestData: RequestUserRegistrationData): Result<ResponseUserRegistration> {
        return try {
            val response = apiService.postUserRegistration(requestData)
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