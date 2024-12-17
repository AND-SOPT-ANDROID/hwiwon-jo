package org.sopt.and.domain.repository

import org.sopt.and.domain.entity.LoginEntity
import org.sopt.and.domain.entity.TokenEntity

interface LoginRepository {
    suspend fun postLogin(loginEntity: LoginEntity): Result<TokenEntity>
}