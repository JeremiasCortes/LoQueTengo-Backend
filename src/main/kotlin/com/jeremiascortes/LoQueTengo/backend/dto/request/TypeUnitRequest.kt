package com.jeremiascortes.LoQueTengo.backend.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class TypeUnitRequest(
    @field:NotBlank(message = "El nombre del tipo de unidad es obligatorio")
    @field:Size(max = 32, message = "El campo máximo debe contener 32 caracteres")
    var name: String
)