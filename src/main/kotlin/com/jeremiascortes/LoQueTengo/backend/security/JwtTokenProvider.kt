package com.jeremiascortes.LoQueTengo.backend.security

import com.jeremiascortes.LoQueTengo.backend.entity.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret}")
    private val secret: String,

    @Value("\${jwt.expiration-minutes}")
    private val expirationMinutes: Long
) {

    private val key: SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret))

    /**
     * Genera un token JWT para un usuario específico utilizando su información.
     *
     * @param user El usuario para el cual se generará el token. La información relevante del usuario,
     * como su correo electrónico y su identificador, se incluirá como claims dentro del token.
     * @return El token JWT generado como una cadena de texto.
     */
    fun generateToken(user: User): String {
        val now = Date()
        val expiration = Date(now.time + expirationMinutes * 60_000)

        return Jwts.builder()
            .subject(user.email)
            .claim("userId", user.id.toString())
            .issuedAt(now)
            .expiration(expiration)
            .signWith(key, Jwts.SIG.HS256)
            .compact()
    }

    /**
     * Obtiene el correo electrónico asociado a un token JWT.
     *
     * Este método extrae la información del 'subject' del payload contenido en el token.
     * En caso de error durante la validación del token o si el formato no es válido,
     * se devolverá `null`.
     *
     * @param token El token JWT del cual se desea extraer el correo electrónico.
     * @return El correo electrónico extraído del token si es válido, o `null` si ocurre algún error.
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
     * Obtiene el identificador único (UUID) del usuario a partir de un token JWT.
     *
     * @param token El token JWT del cual se extraerá el UUID del usuario.
     * @return El UUID del usuario si el token es válido y contiene dicho dato, o null si no es válido
     *         o no contiene un UUID de usuario.
     */
    fun getUserIdFromToken(token: String): UUID? {
        return try {
            val claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .payload
            UUID.fromString(claims["userId"] as String)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Valida un token JWT para determinar si es válido.
     *
     * @param token El token JWT que se desea validar.
     * @return `true` si el token es válido, de lo contrario, `false`.
     */
    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
            true
        } catch (e: Exception) {
            false
        }
    }
}