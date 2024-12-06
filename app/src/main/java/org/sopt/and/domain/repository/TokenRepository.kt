package org.sopt.and.domain.repository

import org.sopt.and.domain.entity.TokenEntity

interface TokenRepository {
    fun getToken(): String
    fun setToken(token: TokenEntity)
}