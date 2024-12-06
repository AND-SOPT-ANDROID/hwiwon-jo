package org.sopt.and.data.datasourceimpl

import org.sopt.and.data.datasource.LoginDataSource
import org.sopt.and.data.model.request.LoginRequestDto
import org.sopt.and.data.model.response.LoginResponseDto
import org.sopt.and.data.service.AuthService
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : LoginDataSource {
    override suspend fun postLogin(requestData: LoginRequestDto): Result<LoginResponseDto> {
        return try {
            val response = authService.postLogin(requestData)
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