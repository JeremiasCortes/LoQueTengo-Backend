package com.jeremiascortes.LoQueTengo.backend.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.*

data class UnitRequest(
    @field:NotBlank(message = "El nombre de la unidad no puede estar vacío")
    @field:Size(max = 32, message = "El nombre de la unidad no puede tener más de 32 caracteres")
    var name: String,

    @field:NotBlank(message = "La abreviatura de la unidad no puede estar vacía")
    @field:Size(max = 8, message = "La abreviatura de la unidad no puede tener más de 8 caracteres")
    var abbreviation: String,

    @field:NotBlank(message = "El ID del tipo de unidad no puede estar vacío")
    var typeUnitId: UUID
)
