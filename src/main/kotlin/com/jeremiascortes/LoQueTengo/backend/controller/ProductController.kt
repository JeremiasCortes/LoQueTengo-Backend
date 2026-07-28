package com.jeremiascortes.LoQueTengo.backend.controller

import com.jeremiascortes.LoQueTengo.backend.dto.request.ProductRequest
import com.jeremiascortes.LoQueTengo.backend.dto.response.ApiResponse
import com.jeremiascortes.LoQueTengo.backend.dto.response.ProductResponse
import com.jeremiascortes.LoQueTengo.backend.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/product")
class ProductController(
    private val productService: ProductService
) {

    @GetMapping
    fun getAllProducts(): ResponseEntity<ApiResponse<List<ProductResponse>>> =
        ResponseEntity.ok(
            ApiResponse.success(
                productService.findAll().map { ProductResponse.fromEntity(it) }
            )
        )

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: UUID): ResponseEntity<ApiResponse<ProductResponse>> =
        ResponseEntity.ok(
            ApiResponse.success(
                ProductResponse.fromEntity(productService.findById(id))
            )
        )

    @GetMapping("/name/{name}")
    fun getProductByName(@PathVariable name: String): ResponseEntity<ApiResponse<ProductResponse>> =
        ResponseEntity.ok(
            ApiResponse.success(
                ProductResponse.fromEntity(productService.findByName(name))
            )
        )

    @GetMapping("/barcode/{barCode}")
    fun getProductByBarcode(@PathVariable barCode: String): ResponseEntity<ApiResponse<List<ProductResponse>>> =
        ResponseEntity.ok(
            ApiResponse.success(
                productService.findByBarCode(barCode).map { ProductResponse.fromEntity(it) }
            )
        )

    @GetMapping("/category/{categoryId}")
    fun getProductByCategory(@PathVariable categoryId: UUID): ResponseEntity<ApiResponse<List<ProductResponse>>> =
        ResponseEntity.ok(
            ApiResponse.success(
                productService.findByCategory(categoryId).map { ProductResponse.fromEntity(it) }
            )
        )

    @PostMapping
    fun createProduct(
        @RequestBody product: ProductRequest
    ): ResponseEntity<ApiResponse<ProductResponse>> {
        return ResponseEntity.ok(
            ApiResponse.success(
                ProductResponse.fromEntity(productService.create(product))
            )
        )
    }

    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable id: UUID,
        @RequestBody requestt: ProductRequest
    ): ResponseEntity<ApiResponse<ProductResponse>> {
        val product = productService.update(
            id = id,
            request = requestt
        )

        return ResponseEntity.ok(
            ApiResponse.success(
                ProductResponse.fromEntity(
                    product
                )
            )
        )
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: UUID): ResponseEntity<ApiResponse<Unit>> {
        productService.delete(id)

        return ResponseEntity.ok(
            ApiResponse.success(
                data = Unit,
                message = "Producto eliminado"
            )
        )
    }

}