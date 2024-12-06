package org.sopt.and.data.mapper

import org.sopt.and.data.model.request.UserRequestDto
import org.sopt.and.data.model.response.UserResponseDto
import org.sopt.and.domain.entity.UserEntity
import org.sopt.and.domain.entity.UserNoEntity

object UserMapper {
    fun entityToDto(entity: UserEntity): UserRequestDto {
        return UserRequestDto(
            userName = entity.username,
            password = entity.password,
            hobby = entity.hobby
        )
    }

    fun dtoToEntity(dto: UserResponseDto): UserNoEntity {
        return UserNoEntity(
            userNo = dto.result.no
        )
    }
}