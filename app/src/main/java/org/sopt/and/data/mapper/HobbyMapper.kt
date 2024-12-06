package org.sopt.and.data.mapper

import org.sopt.and.data.model.response.MyviewResponseDto
import org.sopt.and.domain.entity.HobbyEntity

object HobbyMapper {
    fun dtoToEntity(dto: MyviewResponseDto): HobbyEntity {
        return HobbyEntity(
            hobby = dto.result.hobby
        )
    }
}