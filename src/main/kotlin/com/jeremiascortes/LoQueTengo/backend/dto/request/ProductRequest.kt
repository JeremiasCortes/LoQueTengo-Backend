package com.jeremiascortes.LoQueTengo.backend.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.*

data class ProductRequest(
    @field:NotBlank(message = "El nombre del producto es obligatorio")
    @field:Size(max = 64, message = "El campo nombre, máximo debe contener 64 caracteres")
    var name: String,

    @field:NotBlank(message = "La categoria de producto es obligatorio")
    var categoryId: UUID,

    var barCode: String? = null
)
