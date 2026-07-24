package com.jeremiascortes.LoQueTengo.backend.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CategoryRequest(
    @field:NotBlank(message = "El nombre de la categoría es obligatorio")
    @field:Size(max = 32, message = "El campo máximo debe contener 32 caracteres")
    var name: String
)
