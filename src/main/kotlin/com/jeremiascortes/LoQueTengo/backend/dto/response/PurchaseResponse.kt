package com.jeremiascortes.LoQueTengo.backend.dto.response

import java.math.BigDecimal
import java.time.Instant

data class PurchaseResponse(
    val date: Instant,
    val total: BigDecimal
)
