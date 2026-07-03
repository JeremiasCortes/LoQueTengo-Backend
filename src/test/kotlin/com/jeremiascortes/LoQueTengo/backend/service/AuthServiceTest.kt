package com.jeremiascortes.LoQueTengo.backend.service

import org.junit.jupiter.api.Assertions.*

import com.jeremiascortes.LoQueTengo.backend.dto.request.LoginRequest
import com.jeremiascortes.LoQueTengo.backend.entity.User
import com.jeremiascortes.LoQueTengo.backend.exception.InvalidCredentialsException
import com.jeremiascortes.LoQueTengo.backend.repository.UserRepository
import com.jeremiascortes.LoQueTengo.backend.security.JwtTokenProvider
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.crypto.password.PasswordEncoder

@ExtendWith(MockKExtension::class)
class AuthServiceTest {

    @RelaxedMockK
    private lateinit var userRepository: UserRepository

    @RelaxedMockK
    private lateinit var passwordEncoder: PasswordEncoder

    @RelaxedMockK
    private lateinit var jwtTokenProvider: JwtTokenProvider

    private lateinit var authService: AuthService

    @BeforeEach
    fun setUp() {
        authService = AuthService(userRepository, passwordEncoder, jwtTokenProvider)
    }

    @Test
    fun `login with valid credentials returns AuthResponse with token`() {
        val request = LoginRequest(email = "test@test.com", password = "password123")
        val user = mockk<User>(relaxed = true) {
            every { email } returns request.email
            every { passwordHash } returns "hash_hasheado"
        }
        every { userRepository.findByEmail(request.email) } returns user
        every { passwordEncoder.matches(request.password, "hash_hasheado") } returns true
        every { jwtTokenProvider.generateToken(user) } returns "jwt-token-123"

        val result = authService.login(request)

        assertNotNull(result)
        assertEquals("jwt-token-123", result.token)
    }

    @Test
    fun `login with non-existing email throws InvalidCredentialsException`() {
        val request = LoginRequest(email = "noexiste@test.com", password = "password123")
        every { userRepository.findByEmail(request.email) } returns null

        assertThrows<InvalidCredentialsException> {
            authService.login(request)
        }
    }

    @Test
    fun `login with wrong password throws InvalidCredentialsException`() {
        val request = LoginRequest(email = "test@test.com", password = "wrongPassword")
        val user = mockk<User> {
            every { email } returns request.email
            every { passwordHash } returns "hash_hasheado"
        }
        every { userRepository.findByEmail(request.email) } returns user
        every { passwordEncoder.matches(request.password, "hash_hasheado") } returns false

        assertThrows<InvalidCredentialsException> {
            authService.login(request)
        }
    }
}