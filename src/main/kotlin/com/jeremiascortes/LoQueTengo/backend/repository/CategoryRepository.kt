package com.jeremiascortes.LoQueTengo.backend.repository

import com.jeremiascortes.LoQueTengo.backend.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID

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
     * Busca una categoría en la base de datos por su identificador único.
     *
     * @param id El identificador único de la categoría que se desea buscar.
     * @return Un objeto `Category` si se encuentra una categoría con el identificador proporcionado,
     * o `null` si no se encuentra ninguna categoría.
     */
    @Query("SELECT c FROM Category c WHERE c.id = :id")
    fun findCategoryById(@Param("id") id: UUID): Category?

    /**
     * Busca una categoría en la base de datos por su nombre.
     *
     * @param name El nombre de la categoría que se desea buscar.
     * @return Un objeto `Category` si se encuentra una categoría con el nombre especificado,
     * o `null` si no se encuentra ninguna categoría.
     */
    @Query("SELECT c FROM Category c WHERE c.name = :name")
    fun findCategoryByName(@Param("name") name: String): Category?
}