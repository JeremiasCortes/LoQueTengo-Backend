package com.jeremiascortes.LoQueTengo.backend.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

class LoginRequest(
    @field:NotBlank(message = "El email es obligatorio")
    @field:Email(message = "Debe ser un email válido")
    val email: String,

    @field:NotBlank(message = "La contraseña es obligatoria")
    val password: String
)