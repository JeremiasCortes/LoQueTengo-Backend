package com.jeremiascortes.LoQueTengo.backend.service

import com.jeremiascortes.LoQueTengo.backend.dto.request.LoginRequest
import com.jeremiascortes.LoQueTengo.backend.dto.response.AuthResponse
import com.jeremiascortes.LoQueTengo.backend.exception.InvalidCredentialsException
import com.jeremiascortes.LoQueTengo.backend.repository.UserRepository
import com.jeremiascortes.LoQueTengo.backend.security.JwtTokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider
) {
    @Transactional
    fun login(request: LoginRequest): AuthResponse {
        val user = userRepository.findByEmail(request.email)
            ?: throw InvalidCredentialsException()

        if (!passwordEncoder.matches(request.password, user.passwordHash.orEmpty())) {
            throw InvalidCredentialsException()
        }

        val token = jwtTokenProvider.generateToken(user)
        return AuthResponse.from(token, user)
    }
}