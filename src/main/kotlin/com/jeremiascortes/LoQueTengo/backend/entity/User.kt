package com.jeremiascortes.LoQueTengo.backend.entity

import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction

/**
 * Entidad `User` para la autenticación
 */
@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users Set is_deleted = true, deleted_at = now(), updated_at = now() WHERE id = ?")
@SQLRestriction("is_deleted = false")
class User(

    @Column(unique = true, nullable = true, length = 128)
    val email: String? = null,

    @Column(name = "password_hash")
    var passwordHash: String? = null,

    @Column(nullable = false)
    var enabled: Boolean = true

): BaseEntity() {

    /**
     * Verifica si el usuario es un usuario OAuth
     */
//    fun isOauthUser(): Boolean = authProvider != AuthProvider.LOCAL

    /**
     * Verifica si el usuario tiene contraseña configurada
     */
    fun hasPassword(): Boolean = !passwordHash.isNullOrBlank()

    companion object {
        fun createLocal(email: String, passwordHash: String): User = User(
            email = email,
            passwordHash = passwordHash,
//            authProvider = AuthProvider.LOCAL
        )

        fun createOAuth(
            provider: AuthProvider,
            providerId: String,
            email: String? = null
        ): User = User(
            email = email,
//            authProvider = provider,
//            providerId = providerId
        )
    }

}