package com.jeremiascortes.LoQueTengo.backend.controller

import com.jeremiascortes.LoQueTengo.backend.dto.request.CreateLocalUserRequest
import com.jeremiascortes.LoQueTengo.backend.dto.request.CreateOAuthUserRequest
import com.jeremiascortes.LoQueTengo.backend.dto.request.LoginRequest
import com.jeremiascortes.LoQueTengo.backend.dto.response.ApiResponse
import com.jeremiascortes.LoQueTengo.backend.dto.response.AuthResponse
import com.jeremiascortes.LoQueTengo.backend.dto.response.UserResponse
import com.jeremiascortes.LoQueTengo.backend.service.AuthService
import com.jeremiascortes.LoQueTengo.backend.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val userService: UserService,
    private val authService: AuthService
) {

    @PostMapping("/oauth")
    fun createOAuthUser(
        @Valid @RequestBody
        request: CreateOAuthUserRequest
    ): ResponseEntity<ApiResponse<UserResponse>> {
        val user = userService.createOAuthUser(request)

        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse.success(user, "Usuario OAuth creado correctamente")
        )
    }

    @PostMapping("/register")
    fun createLocalUser(
        @Valid @RequestBody
        request: CreateLocalUserRequest
    ): ResponseEntity<ApiResponse<UserResponse>> {
        val user = userService.createLocalUser(request)

        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse.success(user, "Usuario creado correctamente")
        )
    }

    @PostMapping("/login")
    fun login(
        @Valid @RequestBody
        request: LoginRequest
    ): ResponseEntity<ApiResponse<AuthResponse>> {
        val authResponse = authService.login(request)

        return ResponseEntity.ok(ApiResponse.success(authResponse, "Login exitoso"))
    }
}
