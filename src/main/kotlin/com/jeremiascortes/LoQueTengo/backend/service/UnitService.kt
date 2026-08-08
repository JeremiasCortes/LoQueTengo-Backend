package com.jeremiascortes.LoQueTengo.backend.service

import com.jeremiascortes.LoQueTengo.backend.dto.request.UnitRequest
import com.jeremiascortes.LoQueTengo.backend.entity.Unit
import com.jeremiascortes.LoQueTengo.backend.repository.UnitRepository
import com.jeremiascortes.LoQueTengo.backend.security.SecurityContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UnitService(
    private val unitRespository: UnitRepository,
    securityContext: SecurityContext
) : BaseService(securityContext) {
    private fun validateUnitIsAvailable(request: UnitRequest, userId: UUID) {
        unitRespository.findUnitByNameAndUserId(
            name = request.name,
            userId = userId
        ) ?: throw IllegalArgumentException("Nombre unidad ya existe")

        unitRespository.findUnitByAbbreviationAndUserId(
            abbreviation = request.abbreviation,
            userId = userId
        ) ?: throw IllegalArgumentException("Abreviación de unidad ya existe")

    }

    @Transactional(readOnly = true)
    fun getAllUnits(): List<Unit> = unitRespository.findAllUnitsByUserId(currentUserId())

    @Transactional(readOnly = true)
    fun getUnitById(id: UUID): Unit =
        unitRespository.findUnitByIdAndUserId(id, currentUserId())
            ?: throw IllegalArgumentException("Unidad no encontrada")

    @Transactional(readOnly = true)
    fun getUnitByName(name: String): Unit =
        unitRespository.findUnitByNameAndUserId(name, currentUserId())
            ?: throw IllegalArgumentException("Unidad no encontrada")

    @Transactional(readOnly = true)
    fun getUnitByAbbreviation(abbreviation: String): Unit =
        unitRespository.findUnitByAbbreviationAndUserId(abbreviation, currentUserId())
            ?: throw IllegalArgumentException("Unidad no encontrada")

    @Transactional(readOnly = true)
    fun getUnitsByTypeUnitId(typeUnitId: UUID): List<Unit> =
        unitRespository.findUnitsByTypeIdAndUserId(typeUnitId, currentUserId())

    @Transactional
    fun createUnit(request: UnitRequest): Unit {
        val userId = currentUserId()

        validateUnitIsAvailable(
            request = request,
            userId = userId
        )

        val unit = Unit(
            name = request.name,
            abbreviation = request.abbreviation,
            typeUnitId = request.typeUnitId,
            userId = userId
        )

        return unitRespository.save(unit)
    }

    @Transactional
    fun editUnit(
        id: UUID,
        request: UnitRequest
    ): Unit {
        validateUnitIsAvailable(
            request = request,
            userId = currentUserId()
        )

        val unit = getUnitById(id)

        unit.name = request.name
        unit.abbreviation = request.abbreviation
        unit.typeUnitId = request.typeUnitId

        return unitRespository.save(unit)
    }

    @Transactional
    fun deleteUnit(id: UUID) = getUnitById(id).let { unitRespository.delete(it) }
}