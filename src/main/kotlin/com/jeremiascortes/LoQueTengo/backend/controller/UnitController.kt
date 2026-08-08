package com.jeremiascortes.LoQueTengo.backend.controller

import com.jeremiascortes.LoQueTengo.backend.dto.request.UnitRequest
import com.jeremiascortes.LoQueTengo.backend.dto.response.ApiResponse
import com.jeremiascortes.LoQueTengo.backend.dto.response.UnitResponse
import com.jeremiascortes.LoQueTengo.backend.service.UnitService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/units")
class UnitController(
    private val unitService: UnitService
) {
    @GetMapping
    fun getAll(): ResponseEntity<ApiResponse<List<UnitResponse>>> =
        ResponseEntity.ok(
            ApiResponse.success(
                unitService.getAllUnits().map { UnitResponse.fromEntity(it) }
            )
        )

    @GetMapping("/{id}")
    fun getById(
        @PathVariable id: UUID
    ): ResponseEntity<ApiResponse<UnitResponse>> =
        ResponseEntity.ok(
            ApiResponse.success(
                UnitResponse.fromEntity(unitService.getUnitById(id))
            )
        )

    @GetMapping("/name/{name}")
    fun getByName(
        @PathVariable name: String
    ): ResponseEntity<ApiResponse<UnitResponse>> =
        ResponseEntity.ok(
            ApiResponse.success(
                UnitResponse.fromEntity(unitService.getUnitByName(name))
            )
        )

    @GetMapping("/abbreviation/{abbreviation}")
    fun getByAbbreviation(
        @PathVariable abbreviation: String
    ): ResponseEntity<ApiResponse<UnitResponse>> =
        ResponseEntity.ok(
            ApiResponse.success(
                UnitResponse.fromEntity(unitService.getUnitByAbbreviation(abbreviation))
            )
        )

    @GetMapping("/type-unit/{id}")
    fun getByTypeUnit(
        @PathVariable id: UUID
    ): ResponseEntity<ApiResponse<List<UnitResponse>>> =
        ResponseEntity.ok(
            ApiResponse.success(
                unitService.getUnitsByTypeUnitId(id).map { UnitResponse.fromEntity(it) }
            )
        )

    @PostMapping
    fun create(
        @RequestBody request: UnitRequest
    ): ResponseEntity<ApiResponse<UnitResponse>> =
        ResponseEntity.ok(
            ApiResponse.success(
                UnitResponse.fromEntity(unitService.createUnit(request))
            )
        )

    @PutMapping("/{id}")
    fun edit(
        @PathVariable id: UUID,
        @RequestBody request: UnitRequest
    ): ResponseEntity<ApiResponse<UnitResponse>> =
        ResponseEntity.ok(
            ApiResponse.success(
                UnitResponse.fromEntity(unitService.editUnit(id, request))
            )
        )

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: UUID
    ): ResponseEntity<ApiResponse<Unit>> =
        ResponseEntity.ok(
            ApiResponse.success(
                unitService.deleteUnit(id)
            )
        )
}