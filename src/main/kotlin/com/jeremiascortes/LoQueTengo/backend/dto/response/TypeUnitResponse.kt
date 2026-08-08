package com.jeremiascortes.LoQueTengo.backend.dto.response

import com.jeremiascortes.LoQueTengo.backend.entity.TypeUnit
import java.util.UUID

data class TypeUnitResponse(
    val id: UUID? = null,
    val name: String
) {
    companion object {
        fun fromEntity(typeUnit: TypeUnit): TypeUnitResponse =
            TypeUnitResponse(
                id = typeUnit.id,
                name = typeUnit.name
            )
    }
}
