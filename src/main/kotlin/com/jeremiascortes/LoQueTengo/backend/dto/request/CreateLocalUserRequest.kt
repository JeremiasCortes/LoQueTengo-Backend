package com.jeremiascortes.LoQueTengo.backend.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateLocalUserRequest(
    @field:NotBlank(message = "El email es obligatorio")
    @field:Email(message = "Debe ser un email válido")
    val email: String,

    @field:NotBlank(message = "La contraseña es obligatoria")
    @field:Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    val password: String
)
