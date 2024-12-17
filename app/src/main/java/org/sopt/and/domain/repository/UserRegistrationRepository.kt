package org.sopt.and.domain.repository

import org.sopt.and.domain.entity.UserEntity
import org.sopt.and.domain.entity.UserNoEntity

interface UserRegistrationRepository {
    suspend fun registerUser(user: UserEntity): Result<UserNoEntity>
}