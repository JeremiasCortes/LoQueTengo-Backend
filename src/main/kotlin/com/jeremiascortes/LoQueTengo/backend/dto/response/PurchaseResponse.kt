package com.jeremiascortes.LoQueTengo.backend.dto.response

import com.jeremiascortes.LoQueTengo.backend.entity.Purchase
import java.math.BigDecimal
import java.time.Instant

data class PurchaseResponse(
    val date: Instant,
    val total: BigDecimal
) {
    companion object {
        fun fromEntity(entity: Purchase): PurchaseResponse =
            PurchaseResponse(
                date = entity.date,
                total = entity.total
            )
    }
}
