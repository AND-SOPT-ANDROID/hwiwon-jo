package org.sopt.and.domain.repository

import org.sopt.and.domain.entity.HobbyEntity

interface MyviewRepository {
    suspend fun getHobby(token: String): Result<HobbyEntity>
}