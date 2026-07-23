package com.jeremiascortes.LoQueTengo.backend.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*
import com.jeremiascortes.LoQueTengo.backend.entity.Unit

@Repository
interface UnitRepository : JpaRepository<Unit, UUID> {

    /**
     * Busca una unidad en la base de datos por su identificador único.
     *
     * @param id El identificador único (UUID) de la unidad que se desea buscar.
     * @return La unidad encontrada que coincide con el identificador proporcionado o `null` si no se encuentra ninguna unidad.
     */
    @Query("SELECT u FROM Unit u WHERE u.id = :id")
    fun findUnitById(@Param("id") id: UUID): Unit?

    /**
     * Busca una unidad en la base de datos por su nombre.
     *
     * @param name El nombre de la unidad que se desea buscar.
     * @return Un objeto [Unit] si se encuentra una unidad con el nombre especificado,
     * o `null` si no se encuentra ninguna unidad con dicho nombre.
     */
    @Query("SELECT u FROM Unit u WHERE u.name = :name")
    fun findUnitByName(@Param("name") name: String): Unit?

    /**
     * Busca una unidad en la base de datos por su abreviatura.
     *
     * @param abbreviation La abreviatura de la unidad que se desea buscar.
     * @return Un objeto [Unit] si se encuentra una unidad con la abreviatura especificada, o `null` si no se encuentra ninguna unidad.
     */
    @Query("SELECT u FROM Unit u WHERE u.abbreviation = :abbreviation")
    fun findUnitByAbbreviation(@Param("abbreviation") abbreviation: String): Unit?

    /**
     * Busca una lista de unidades en la base de datos que están asociadas con un tipo de unidad específico.
     *
     * @param typeId El identificador único (UUID) del tipo de unidad que se desea buscar.
     * @return Una lista de objetos [Unit] que están asociados con el identificador de tipo proporcionado.
     * Si no se encuentra ninguna unidad asociada, la lista estará vacía.
     */
    @Query("SELECT u FROM Unit u WHERE u.typeUnit.id = :typeId")
    fun findUnitsByTypeId(@Param("typeId") typeId: UUID): List<Unit>
}