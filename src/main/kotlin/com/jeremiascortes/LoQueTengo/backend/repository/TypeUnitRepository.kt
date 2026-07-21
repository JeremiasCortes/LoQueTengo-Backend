package com.jeremiascortes.LoQueTengo.backend.repository

import com.jeremiascortes.LoQueTengo.backend.entity.TypeUnit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TypeUnitRepository : JpaRepository<TypeUnit, UUID> {

    @Query("SELECT tyut FROM TypeUnit tyut WHERE tyut.id = :id")
    fun findTypeUnitById(@Param("id") id: UUID): TypeUnit?

    @Query("SELECT tyut FROM TypeUnit tyut")
    fun findAllTypeUnit(): List<TypeUnit>
}