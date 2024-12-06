package org.sopt.and.data.datasourceimpl

import org.sopt.and.data.datasource.UserDataSource
import org.sopt.and.data.model.request.UserRequestDto
import org.sopt.and.data.model.response.UserResponseDto
import org.sopt.and.data.service.AuthService
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : UserDataSource {
    override suspend fun postUserRegistration(request: UserRequestDto): Result<UserResponseDto> {
        return try {
            val response = authService.postUserRegistration(request)
            if (response.isSuccessful) {
                response.body()?.let { Result.success(it) }
                    ?: Result.failure(Exception("Response body가 null"))
            } else {
                val errorCode = response.errorBody()?.string() ?: "알수없슴"
                Result.failure(Exception("Error code: ${response.code()}, 에러 코드: $errorCode"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}