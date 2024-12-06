package org.sopt.and.data.repository.Auth

import org.sopt.and.data.api.LoginService
import org.sopt.and.data.dto.RequestLoginData
import org.sopt.and.data.dto.ResponseLogin
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiService: LoginService
) {
    suspend fun postLogin(requestData: RequestLoginData): Result<ResponseLogin> {
        return try {
            val response = apiService.postLogin(requestData)
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