package com.jeremiascortes.LoQueTengo.backend.controller

import com.jeremiascortes.LoQueTengo.backend.dto.request.TypeUnitRequest
import com.jeremiascortes.LoQueTengo.backend.dto.response.ApiResponse
import com.jeremiascortes.LoQueTengo.backend.dto.response.TypeUnitResponse
import com.jeremiascortes.LoQueTengo.backend.service.TypeUnitService
import org.apache.coyote.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/type-unit")
class TypeUnitController(
    private val typeUnitService: TypeUnitService
) {
    @GetMapping
    fun getAll(): ResponseEntity<ApiResponse<List<TypeUnitResponse>>> =
        ResponseEntity.ok(
            ApiResponse.success(
                typeUnitService.getAll().map { TypeUnitResponse.fromEntity(it) }
            )
        )

    @GetMapping("{id}")
    fun getById(
        @PathVariable id: UUID
    ): ResponseEntity<ApiResponse<TypeUnitResponse>> =
        ResponseEntity.ok(
            ApiResponse.success(
                TypeUnitResponse.fromEntity(typeUnitService.findById(id))
            )
        )

    @PostMapping
    fun create(
        @RequestBody typeUnit: TypeUnitRequest,
    ): ResponseEntity<ApiResponse<TypeUnitResponse>> =
        ResponseEntity.ok(
            ApiResponse.success(
                TypeUnitResponse.fromEntity(typeUnitService.create(typeUnit))
            )
        )

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: UUID,
        @RequestBody request: TypeUnitRequest
    ): ResponseEntity<ApiResponse<TypeUnitResponse>> {
        val typeUnit = typeUnitService.update(
            id = id,
            request = request
        )

        return ResponseEntity.ok(
            ApiResponse.success(
                TypeUnitResponse.fromEntity(typeUnit)
            )
        )
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(
        @PathVariable id: UUID
    ): ResponseEntity<ApiResponse<Unit>> {
        typeUnitService.delete(id)

        return ResponseEntity.ok(
            ApiResponse.success(
                data = Unit,
                message = "Tìpo de unidad eliminado"
            )
        )
    }
}