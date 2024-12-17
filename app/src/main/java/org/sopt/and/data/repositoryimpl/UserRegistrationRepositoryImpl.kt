package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.datasource.UserDataSource
import org.sopt.and.data.mapper.UserMapper
import org.sopt.and.domain.entity.UserEntity
import org.sopt.and.domain.entity.UserNoEntity
import org.sopt.and.domain.repository.UserRegistrationRepository
import javax.inject.Inject

class UserRegistrationRepositoryImpl @Inject constructor(
    private val dataSource: UserDataSource
) : UserRegistrationRepository {
    override suspend fun registerUser(user: UserEntity): Result<UserNoEntity> {
        val requestDto = UserMapper.entityToDto(user)
        val response = dataSource.postUserRegistration(requestDto)
        return response.mapCatching { UserMapper.dtoToEntity(it) }
    }
}