package com.jeremiascortes.LoQueTengo.backend.dto.response

import com.jeremiascortes.LoQueTengo.backend.entity.Product
import java.util.*

data class ProductResponse(
    val id: UUID? = null,
    val name: String,
    val category: UUID,
    val barCode: String? = null
) {
    companion object {
        fun fromEntity(product: Product): ProductResponse {
            return ProductResponse(
                id = product.id,
                name = product.name,
                category = product.category.id!!,
                barCode = product.barCode
            )
        }
    }
}
