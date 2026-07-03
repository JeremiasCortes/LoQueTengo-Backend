package com.jeremiascortes.LoQueTengo.backend.security

import com.jeremiascortes.LoQueTengo.backend.entity.User
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date
import javax.crypto.SecretKey

/**
 * Componente responsable de generar y validar tokens JWT
 */
@Component
class JwtTokenProvider(
    @Value("\${jwt.secret}")
    private val secret: String,

    @Value("\${jwt.expiration-minutes}")
    private val expirationMinutes: Long
) {

    private val key: SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret))

    /**
     * Genera un token JWT para un usuario específico.
     *
     * @param user La entidad `User` para la cual se generará el token.
     *        Se utiliza el campo `email` de este usuario como sujeto del token.
     * @return Un token JWT en formato `String`, que contiene la información del usuario y una fecha de expiración.
     */
    fun generateToken(user: User): String {
        val now = Date()
        val expiration = Date(now.time + expirationMinutes * 60_000)

        return Jwts.builder()
            .subject(user.email)
            .issuedAt(now)
            .expiration(expiration)
            .signWith(key, Jwts.SIG.HS256)
            .compact()
    }

    /**
     * Extrae la dirección de correo electrónico contenida en el token JWT.
     *
     * @param token El token JWT a analizar.
     * @return La dirección de correo electrónico (subject) del payload del token si es válida,
     *         o `null` si el token no puede ser procesado o es inválido.
     */
    fun getEmailFromToken(token: String): String? {
        return try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .payload
                .subject
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Valida si un token JWT es válido según las reglas de firma y formato.
     *
     * @param token El token JWT a validar.
     * @return `true` si el token es válido, o `false` si es inválido, está expirado, mal formado
     *         o no cumple con los criterios esperados.
     */
    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
            true // Token válido
        } catch (e: ExpiredJwtException){
            false // Token expirado
        } catch (e: MalformedJwtException) {
            false // Token mal formado
        } catch (e: UnsupportedJwtException) {
            false // Formato del token no esperado
        } catch (e: IllegalArgumentException) {
            false // Token vacío o null
        } catch (e: Exception) {
            false // Cualquier otro error
        }
    }
}