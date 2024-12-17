package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.datasource.HobbyDataSource
import org.sopt.and.data.mapper.HobbyMapper
import org.sopt.and.domain.entity.HobbyEntity
import org.sopt.and.domain.repository.MyviewRepository
import javax.inject.Inject

class MyviewRepositoryImpl @Inject constructor(
    private val dataSource: HobbyDataSource
) : MyviewRepository {
    override suspend fun getHobby(
        token: String
    ): Result<HobbyEntity> {
        return try {
            val response = dataSource.getHobby(token)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(HobbyMapper.dtoToEntity(body))
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