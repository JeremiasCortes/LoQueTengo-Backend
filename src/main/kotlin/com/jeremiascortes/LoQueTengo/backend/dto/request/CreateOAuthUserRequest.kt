package com.jeremiascortes.LoQueTengo.backend.dto.request

import com.jeremiascortes.LoQueTengo.backend.entity.AuthProvider
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateOAuthUserRequest(
    @field:NotNull(message = "El proveedor es obligatorio")
    val provider: AuthProvider,

    @field:NotBlank(message = "El ID del proveedor es obligatorio")
    val providerId: String,

    val email: String? = null
)