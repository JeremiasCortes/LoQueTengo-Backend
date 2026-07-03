package com.jeremiascortes.LoQueTengo.backend.security

import com.jeremiascortes.LoQueTengo.backend.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("No existe el usuario con email: $email")

        return User
            .withUsername(user.email!!)
            .password(user.passwordHash.orEmpty())
            .authorities(emptyList())
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(!user.enabled)
            .build()
    }
}