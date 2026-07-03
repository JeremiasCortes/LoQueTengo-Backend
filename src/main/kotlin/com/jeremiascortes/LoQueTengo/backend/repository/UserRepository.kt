package com.jeremiascortes.LoQueTengo.backend.repository

import com.jeremiascortes.LoQueTengo.backend.entity.AuthProvider
import com.jeremiascortes.LoQueTengo.backend.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID> {

    /**
     * Busca un usuario en la base de datos por su correo electrónico.
     *
     * @param email El correo electrónico del usuario que se desea buscar.
     * @return Un objeto `User` si se encuentra un usuario con el correo especificado, o `null` si no se encuentra ningún usuario.
     */
    @Query("SELECT u FROM User u WHERE u.email = :email")
    fun findByEmail(@Param("email") email: String): User?

    /**
     * Busca un usuario en la base de datos utilizando un proveedor de autenticación
     * específico y un identificador único del proveedor.
     *
     * @param provider El proveedor de autenticación utilizado por el usuario (por ejemplo, GOOGLE, GITHUB).
     * @param providerId El identificador único asignado al usuario por el proveedor de autenticación.
     * @return Un objeto `User` si se encuentra un usuario que coincide con el proveedor de autenticación
     * y el identificador proporcionados, o `null` si no se encuentra ningún usuario.
     */
    @Query("SELECT u FROM User u WHERE u.authProvider = :provider AND u.providerId = u.providerId")
    fun findByOAuthAccount(
        @Param("provider") provider: AuthProvider,
        @Param("providerId") providerId: String
    ): User?

    /**
     * Verifica si existe un usuario en la base de datos con el correo electrónico especificado.
     *
     * @param email El correo electrónico del usuario que se desea verificar.
     * @return `true` si existe un usuario con el correo especificado, de lo contrario `false`.
     */
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = :email")
    fun existsByEmail(@Param("email") email: String): Boolean

    /**
     * Verifica si existe un usuario en la base de datos asociado a un proveedor
     * de autenticación específico y un identificador único del proveedor.
     *
     * @param provider El proveedor de autenticación utilizado por el usuario (por ejemplo, GOOGLE, GITHUB, LOCAL).
     * @param providerId El identificador único asignado al usuario por el proveedor de autenticación.
     * @return `true` si existe un usuario que coincide con el proveedor de autenticación
     * y el identificador proporcionados, o `false` si no se encuentra ningún usuario.
     */
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.authProvider = :provider AND u.providerId = :providerId")
    fun existsByOauthAccount(
        @Param("provider") provider: AuthProvider,
        @Param("providerId") providerId: String
    ): Boolean
}
