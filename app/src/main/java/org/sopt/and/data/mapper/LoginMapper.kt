package org.sopt.and.data.mapper

import org.sopt.and.data.model.request.LoginRequestDto
import org.sopt.and.data.model.response.LoginResponseDto
import org.sopt.and.domain.entity.LoginEntity
import org.sopt.and.domain.entity.TokenEntity

object LoginMapper {
    fun entityToDto(entity: LoginEntity): LoginRequestDto {
        return LoginRequestDto(
            userName = entity.username,
            password = entity.password
        )
    }

    fun dtoToEntity(dto: LoginResponseDto): TokenEntity {
        return TokenEntity(
            token = dto.result.token
        )
    }
}