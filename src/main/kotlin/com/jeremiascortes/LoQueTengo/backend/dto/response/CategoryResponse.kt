package com.jeremiascortes.LoQueTengo.backend.dto.response

import com.jeremiascortes.LoQueTengo.backend.entity.Category
import java.util.*

data class CategoryResponse(
    val id: UUID? = null,
    val name: String
) {
    companion object {
        fun fromEntity(category: Category): CategoryResponse =
            CategoryResponse(
                id = category.id,
                name = category.name
            )
    }
}