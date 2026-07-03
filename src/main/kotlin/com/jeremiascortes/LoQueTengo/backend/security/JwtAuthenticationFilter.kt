package com.jeremiascortes.LoQueTengo.backend.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    private val log = LoggerFactory.getLogger(JwtAuthenticationFilter::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val token = getTokenFromRequest(request)

            if (token != null && jwtTokenProvider.validateToken(token)) {
                val email = jwtTokenProvider.getEmailFromToken(token)

                if (email != null) {
                    val userDetails = userDetailsService.loadUserByUsername(email)

                    val authentication = UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.authorities
                    )

                    SecurityContextHolder.getContext().authentication = authentication

                    log.debug("Usuario autenticado desde JWT: {}", email)
                }
            }
        } catch (e: Exception) {
            log.error("Error al autenticar usuario desde JWT: {}", e.message)
        }

        filterChain.doFilter(request, response)
    }

    private fun getTokenFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")

        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else {
            null
        }
    }
}