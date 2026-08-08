package com.jeremiascortes.LoQueTengo.backend.dto.response

import com.jeremiascortes.LoQueTengo.backend.entity.Unit
import java.util.*

data class UnitResponse(
    val id: UUID,
    val name: String,
    val abbreviation: String,
    val typeUnitId: UUID
) {
    companion object {
        fun fromEntity(unit: Unit): UnitResponse{
            return UnitResponse(
                id = unit.id!!,
                name = unit.name,
                abbreviation = unit.abbreviation,
                typeUnitId = unit.typeUnitId
            )
        }
    }
}
