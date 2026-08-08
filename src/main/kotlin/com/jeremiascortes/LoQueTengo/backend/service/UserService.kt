package com.jeremiascortes.LoQueTengo.backend.service

import com.jeremiascortes.LoQueTengo.backend.dto.request.CreateLocalUserRequest
import com.jeremiascortes.LoQueTengo.backend.dto.request.CreateOAuthUserRequest
import com.jeremiascortes.LoQueTengo.backend.dto.response.UserResponse
import com.jeremiascortes.LoQueTengo.backend.entity.User
import com.jeremiascortes.LoQueTengo.backend.exception.EmailAlreadyExistsException
import com.jeremiascortes.LoQueTengo.backend.exception.OAuthAccountAlreadyLinkedException
import com.jeremiascortes.LoQueTengo.backend.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional
    fun createLocalUser(request: CreateLocalUserRequest): UserResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw EmailAlreadyExistsException(request.email)
        }
        val user = User.createLocal(
            email = request.email,
            passwordHash = passwordEncoder.encode(request.password)!!
        )
        return UserResponse.fromEntity(userRepository.save(user))
    }

/*    @Transactional
    fun createOAuthUser(request: CreateOAuthUserRequest): UserResponse {
        userRepository.findByOAuthAccount(request.provider, request.providerId)
            ?.let { throw OAuthAccountAlreadyLinkedException(request.provider) }

        request.email?.let { email ->
            if (userRepository.existsByEmail(email)) {
                throw EmailAlreadyExistsException(email)
            }
        }

        val user = User.createOAuth(
            provider = request.provider,
            providerId = request.providerId,
            email = request.email
        )
        return UserResponse.fromEntity(userRepository.save(user))
    }*/
}