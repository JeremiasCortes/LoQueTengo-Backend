package com.jeremiascortes.LoQueTengo.backend.controller

import com.jeremiascortes.LoQueTengo.backend.dto.request.PurchaseRequest
import com.jeremiascortes.LoQueTengo.backend.dto.response.ApiResponse
import com.jeremiascortes.LoQueTengo.backend.dto.response.PurchaseResponse
import com.jeremiascortes.LoQueTengo.backend.service.PurchaseService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.util.UUID

@RestController
@RequestMapping("/api/v1/purchase")
class PurchaseController(
    private val purchaseService: PurchaseService
) {

    @GetMapping
    fun getAll(): ResponseEntity<ApiResponse<List<PurchaseResponse>>> =
        ResponseEntity.ok(ApiResponse.success(purchaseService.getAll().map { PurchaseResponse.fromEntity(it) }))

    @GetMapping("/{id}")
    fun getById(id: UUID): ResponseEntity<ApiResponse<PurchaseResponse>> =
        ResponseEntity.ok(ApiResponse.success(PurchaseResponse.fromEntity(purchaseService.getById(id))))

    @GetMapping("/date/{date}")
    fun getByDate(@PathVariable date: Instant): ResponseEntity<ApiResponse<List<PurchaseResponse>>> =
        ResponseEntity.ok(ApiResponse.success(purchaseService.getByDate(date).map { PurchaseResponse.fromEntity(it) }))

    @PostMapping
    fun create(@RequestBody purchaseRequest: PurchaseRequest): ResponseEntity<ApiResponse<PurchaseResponse>> =
        ResponseEntity.ok(ApiResponse.success(PurchaseResponse.fromEntity(purchaseService.create(purchaseRequest))))

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: UUID,
        @RequestBody purchaseRequest: PurchaseRequest
    ): ResponseEntity<ApiResponse<PurchaseResponse>> =
        ResponseEntity.ok(
            ApiResponse.success(
                PurchaseResponse.fromEntity(
                    purchaseService.update(
                        id = id,
                        request = purchaseRequest
                    )
                )
            )
        )

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<ApiResponse<Unit>> =
        ResponseEntity.ok(ApiResponse.success(purchaseService.delete(id = id)))
}