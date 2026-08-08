package com.jeremiascortes.LoQueTengo.backend.dto.request

import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.Instant

data class PurchaseRequest(
    @field:NotNull(message = "La fecha es obligatoria")
    val date: Instant,

    @field:NotNull(message = "El total es obligatorio")
    val total: BigDecimal,
)
