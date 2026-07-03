package com.jeremiascortes.LoQueTengo.backend.controller

import com.jeremiascortes.LoQueTengo.backend.dto.response.ApiResponse
import com.jeremiascortes.LoQueTengo.backend.dto.response.UserResponse
import com.jeremiascortes.LoQueTengo.backend.exception.ResourceNotFoundException
import com.jeremiascortes.LoQueTengo.backend.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// Endpoints de gestión del recurso Usuario bajo /api/v1/users.
//
// El registro de usuarios locales vive en AuthController bajo
// /api/v1/auth/register porque es un flujo de autenticación, no de
// gestión de recurso.
//
// Aquí irán:
//   - GET /me  (perfil del usuario autenticado, Fase 3 con JWT)
//   - GET /{id} (búsqueda, etc.)
@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userRepository: UserRepository
) {
    @GetMapping("/me")
    fun me(
        @AuthenticationPrincipal userDetails: UserDetails
    ): ResponseEntity<ApiResponse<UserResponse>> {
        val user = userRepository.findByEmail(userDetails.username)
            ?: throw ResourceNotFoundException("Usuario no encontrado")

        return ResponseEntity.ok(
            ApiResponse.success(
                UserResponse.fromEntity(user),
                "Usuario autenticado correctamente"
            )
        )
    }
}