package org.sopt.and.data.mapper

import org.sopt.and.data.model.response.HobbyResponseDto
import org.sopt.and.domain.entity.HobbyEntity

object HobbyMapper {
    fun dtoToEntity(dto: HobbyResponseDto): HobbyEntity {
        return HobbyEntity(
            hobby = dto.result.hobby
        )
    }
}