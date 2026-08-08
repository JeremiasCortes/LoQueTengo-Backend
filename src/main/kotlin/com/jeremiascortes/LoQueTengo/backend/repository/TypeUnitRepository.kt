package com.jeremiascortes.LoQueTengo.backend.repository

import com.jeremiascortes.LoQueTengo.backend.entity.TypeUnit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TypeUnitRepository : JpaRepository<TypeUnit, UUID> {
    /**
     * Busca un tipo de unidad específico en la base de datos utilizando su identificador único y el identificador del
     * usuario asociado.
     *
     * @param id El identificador único (UUID) del tipo de unidad que se desea buscar.
     * @param userId El identificador único (UUID) del usuario asociado al tipo de unidad.
     * @return El tipo de unidad encontrado que coincide con los identificadores proporcionados,
     * o `null` si no se encuentra ningún registro coincidente.
     */
    @Query("SELECT tyut FROM TypeUnit tyut WHERE tyut.id = :id AND tyut.userId = :userId")
    fun findTypeUnitByIdByUserId(
        @Param("id") id: UUID,
        @Param("userId") userId: UUID
    ): TypeUnit?

    /**
     * Obtiene una lista de todos los objetos `TypeUnit` asociados a un usuario específico.
     *
     * @param userId El identificador único (UUID) del usuario para el cual se desean buscar los objetos `TypeUnit`.
     * @return Una lista de objetos `TypeUnit` asociados al usuario proporcionado. Si no existen objetos asociados, se
     * devuelve una lista vacía.
     */
    @Query("SELECT tyut FROM TypeUnit tyut WHERE tyut.userId = :userId")
    fun findAllTypeUnitByUserId(
        @Param("userId") userId: UUID
    ): List<TypeUnit>
}