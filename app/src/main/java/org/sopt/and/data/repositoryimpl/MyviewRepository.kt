package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.di.HobbyService
import javax.inject.Inject

class MyviewRepository @Inject constructor(
    private val apiService: HobbyService
) {
    suspend fun getHobby(
        token: String
    ): Result<String> {
        return try {
            val response = apiService.getHobby(token)
            if (response.isSuccessful) {
                val hobby = response.body()?.result?.hobby
                if (hobby != null) {
                    Result.success(hobby)
                } else {
                    Result.failure(Exception("Hobby data is null"))
                }
            } else {
                val errorCode = response.code()
                Result.failure(Exception("Error code : $errorCode"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}