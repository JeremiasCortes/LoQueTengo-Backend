package com.jeremiascortes.LoQueTengo.backend.repository

import com.jeremiascortes.LoQueTengo.backend.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

/**
 * Repositorio para la gestión de entidades [Category] en la base de datos.
 *
 * Proporciona métodos para realizar operaciones CRUD y consultas personalizadas
 * sobre las categorías de productos en el sistema.
 *
 * @see JpaRepository
 * @see Category
 */
@Repository
interface CategoryRepository : JpaRepository<Category, UUID> {

    /**
     * Obtiene todas las categorías asociadas a un usuario específico.
     *
     * @param userId El identificador único (UUID) del usuario cuyas categorías se desean recuperar.
     * @return Una lista de objetos [Category] que representan las categorías asociadas al usuario proporcionado.
     * Si no se encuentran categorías, la lista estará vacía.
     */
    @Query("SELECT c FROM Category c WHERE c.user =:userId")
    fun findAllByUserId(
        @Param("userId") userId: UUID
    ): List<Category>

    /**
     * Busca una categoría en la base de datos en función de su identificador y el identificador del usuario asociado.
     *
     * @param userId El identificador único (UUID) del usuario propietario de la categoría.
     * @param id El identificador único (UUID) de la categoría que se desea buscar.
     * @return La categoría encontrada que corresponde al identificador proporcionado y pertenece al usuario especificado,
     * o `null` si no se encuentra ninguna coincidencia.
     */
    @Query("SELECT c FROM Category c WHERE c.id = :id AND c.user = :userId")
    fun findCategoryByIdAndUserId(
        @Param("userId") userId: UUID,
        @Param("id") id: UUID
    ): Category?

    /**
     * Busca una categoría en la base de datos que coincida con un nombre y un usuario específicos.
     *
     * @param userId El identificador único (UUID) del usuario asociado a la categoría.
     * @param name El nombre de la categoría que se desea buscar.
     * @return Un objeto [Category] si se encuentra una categoría que coincida con el nombre y el usuario proporcionados,
     * o `null` si no se encuentra ninguna categoría.
     */
    @Query("SELECT c FROM Category c WHERE c.name = :name AND c.user = :userId")
    fun findCategoryByNameAndUserId(
        @Param("userId") userId: UUID,
        @Param("name") name: String
    ): Category?
}