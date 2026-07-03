package com.jeremiascortes.LoQueTengo.backend.service

import org.junit.jupiter.api.Assertions.*
import com.jeremiascortes.LoQueTengo.backend.dto.request.CreateLocalUserRequest
import com.jeremiascortes.LoQueTengo.backend.dto.request.CreateOAuthUserRequest
import com.jeremiascortes.LoQueTengo.backend.entity.AuthProvider
import com.jeremiascortes.LoQueTengo.backend.entity.User
import com.jeremiascortes.LoQueTengo.backend.exception.EmailAlreadyExistsException
import com.jeremiascortes.LoQueTengo.backend.exception.OAuthAccountAlreadyLinkedException
import com.jeremiascortes.LoQueTengo.backend.repository.UserRepository
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.crypto.password.PasswordEncoder

@ExtendWith(MockKExtension::class)
class UserServiceTest {

    @RelaxedMockK
    private lateinit var userRepository: UserRepository

    @RelaxedMockK
    private lateinit var passwordEncoder: PasswordEncoder

    private lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        userService = UserService(userRepository, passwordEncoder)
    }

    @Test
    fun `createLocalUser with new email returns UserResponse`() {
        val request = CreateLocalUserRequest(
            email = "nuevo@test.com",
            password = "password123"
        )
        every { userRepository.existsByEmail(request.email) } returns false
        every { passwordEncoder.encode(request.password) } returns "hash_hasheado"
        every { userRepository.save(any<User>()) } answers { firstArg() }

        val result = userService.createLocalUser(request)

        assertNotNull(result)
        assertEquals(request.email, result.email)
    }

    @Test
    fun `createLocalUser with existing email throws EmailAlreadyExistsException`() {
        val request = CreateLocalUserRequest(
            email = "existente@test.com",
            password = "password123"
        )
        every { userRepository.existsByEmail(request.email) } returns true

        assertThrows<EmailAlreadyExistsException> {
            userService.createLocalUser(request)
        }
    }

    @Test
    fun `createOAuthUser with new provider and providerId creates user`() {
        val request = CreateOAuthUserRequest(
            provider = AuthProvider.GOOGLE,
            providerId = "google-123",
            email = "nuevo@test.com"
        )
        every { userRepository.findByOAuthAccount(request.provider, request.providerId) } returns null
        every { userRepository.existsByEmail(request.email!!) } returns false
        every { userRepository.save(any<User>()) } answers { firstArg() }

        val result = userService.createOAuthUser(request)

        assertNotNull(result)
        assertEquals(request.email, result.email)
    }

    @Test
    fun `createOAuthUser with existing OAuth account throws OAuthAccountAlreadyLinkedException`() {
        val request = CreateOAuthUserRequest(
            provider = AuthProvider.GOOGLE,
            providerId = "google-123",
            email = null
        )
        val existingUser = io.mockk.mockk<User>()
        every { userRepository.findByOAuthAccount(request.provider, request.providerId) } returns existingUser

        assertThrows<OAuthAccountAlreadyLinkedException> {
            userService.createOAuthUser(request)
        }
    }
}