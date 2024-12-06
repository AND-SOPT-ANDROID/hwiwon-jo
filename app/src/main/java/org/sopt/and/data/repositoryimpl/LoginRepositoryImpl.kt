package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.datasource.LoginDataSource
import org.sopt.and.data.mapper.LoginMapper
import org.sopt.and.domain.entity.LoginEntity
import org.sopt.and.domain.entity.TokenEntity
import org.sopt.and.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource
) : LoginRepository {
    override suspend fun postLogin(loginEntity: LoginEntity): Result<TokenEntity> {
        val requestDto = LoginMapper.entityToDto(loginEntity)
        val response = loginDataSource.postLogin(requestDto)
        return response.mapCatching { LoginMapper.dtoToEntity(it) }
    }
}