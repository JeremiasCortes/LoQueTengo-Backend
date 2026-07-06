package com.jeremiascortes.LoQueTengo.backend.security

import com.jeremiascortes.LoQueTengo.backend.dto.response.ApiResponse
import com.jeremiascortes.LoQueTengo.backend.dto.response.ApiResponse.Companion.error
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import tools.jackson.databind.ObjectMapper

/**
 * Se ejecuta cuando un usuario intenta acceder a un endpoint protegido
 * SIN estar autenticado (o con un token inválido).
 *
 * Por defecto, Spring devolvería un 401 vacío o HTML. Este componente
 * lo personaliza para devolver un 401 en JSON con el formato de
 * [ApiResponse], consistente con el resto de la API.
 */
@Component
class JwtAuthenticationEntryPoint(
    private val objectMapper: ObjectMapper
) : AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(
            objectMapper.writeValueAsString(
                "No estás autenticado. Token inválido, expirado o ausente."
            )
        )
    }
}
