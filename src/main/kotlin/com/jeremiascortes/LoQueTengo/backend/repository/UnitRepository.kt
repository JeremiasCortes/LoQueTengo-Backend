package com.jeremiascortes.LoQueTengo.backend.repository

import com.jeremiascortes.LoQueTengo.backend.entity.Unit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UnitRepository : JpaRepository<Unit, UUID> {

    /**
     * Busca todas las unidades asociadas a un usuario específico.
     *
     * @param userId Identificador único del usuario cuya lista de unidades se desea obtener.
     * @return Una lista de objetos `Unit` correspondientes al usuario especificado.
     */
    @Query("SELECT u FROM Unit u WHERE u.userId = :userId")
    fun findAllUnitsByUserId(
        @Param("userId") userId: UUID
    ): List<Unit>

    /**
     * Busca una unidad en la base de datos utilizando su ID y el ID del usuario asociado.
     *
     * @param id El identificador único de la unidad que se desea buscar.
     * @param userId El identificador único del usuario asociado a la unidad.
     * @return Un objeto `Unit` si se encuentra una unidad que coincide con los parámetros proporcionados, o `null` si no se encuentra ninguna.
     */
    @Query("SELECT u FROM Unit u WHERE u.id = :id AND u.userId = :userId")
    fun findUnitByIdAndUserId(
        @Param("id") id: UUID,
        @Param("userId") userId: UUID
    ): Unit?

    /**
     * Busca una unidad en la base de datos por su nombre y el identificador del usuario asociado.
     *
     * @param name Nombre de la unidad que se desea buscar.
     * @param userId Identificador único del usuario al que pertenece la unidad.
     * @return Un objeto `Unit` si se encuentra una unidad que coincida con el nombre
     * y el identificador proporcionados, o `null` si no se encuentra ninguna unidad.
     */
    @Query("SELECT u FROM Unit u WHERE u.name = :name AND u.userId = :userId")
    fun findUnitByNameAndUserId(
        @Param("name") name: String,
        @Param("userId") userId: UUID
    ): Unit?

    /**
     * Busca una entidad `Unit` en la base de datos utilizando una abreviatura específica
     * y el identificador único de un usuario asociado.
     *
     * @param abbreviation La abreviatura de la unidad que se desea buscar.
     * @param userId El identificador único del usuario asociado a la unidad.
     * @return Una instancia de `Unit` si se encuentra una unidad que coincida con la abreviatura
     * y el identificador del usuario, o `null` si no se encuentra ninguna unidad.
     */
    @Query("SELECT u FROM Unit u WHERE u.abbreviation = :abbreviation AND u.userId = :userId")
    fun findUnitByAbbreviationAndUserId(
        @Param("abbreviation") abbreviation: String,
        @Param("userId") userId: UUID
    ): Unit?

    /**
     * Busca todas las unidades que coincidan con un tipo de unidad específico y un usuario dado.
     *
     * @param typeId Identificador único del tipo de unidad.
     * @param userId Identificador único del usuario asociado.
     * @return Una lista de objetos `Unit` que pertenecen al usuario especificado y tienen el tipo de unidad indicado.
     */
    @Query("SELECT u FROM Unit u WHERE u.typeUnitId = :typeId AND u.userId = :userId")
    fun findUnitsByTypeIdAndUserId(
        @Param("typeId") typeId: UUID,
        @Param("userId") userId: UUID
    ): List<Unit>
}