package com.jeremiascortes.LoQueTengo.backend.service

import com.jeremiascortes.LoQueTengo.backend.dto.request.ProductRequest
import com.jeremiascortes.LoQueTengo.backend.entity.Category
import com.jeremiascortes.LoQueTengo.backend.entity.Product
import com.jeremiascortes.LoQueTengo.backend.exception.ResourceNotFoundException
import com.jeremiascortes.LoQueTengo.backend.repository.CategoryRepository
import com.jeremiascortes.LoQueTengo.backend.repository.ProductRepository
import com.jeremiascortes.LoQueTengo.backend.security.SecurityContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    securityContext: SecurityContext
) : BaseService(securityContext) {

    private fun validCategory(categoryId: UUID): Category =
        categoryRepository.findCategoryByIdAndUserId(
            id = categoryId,
            userId = currentUserId()
        ) ?: throw ResourceNotFoundException("No se pudo encontrar la categoria")

    @Transactional(readOnly = true)
    fun findAll(): List<Product> = productRepository.findAllByUserId(currentUserId())

    @Transactional(readOnly = true)
    fun findById(id: UUID): Product =
        productRepository.findProductByIdAndUserId(
            id = id,
            userId = currentUserId()
        ) ?: throw ResourceNotFoundException("Producto no encontrada")

    fun findByName(name: String): Product =
        productRepository.findProductByNameAndUserId(
            name = name,
            userId = currentUserId()
        ) ?: throw ResourceNotFoundException("Producto no encontrada")

    @Transactional(readOnly = true)
    fun findByCategory(categoryId: UUID): List<Product> =
        productRepository.findProductByCategoryAndUserId(
            categoryId = categoryId,
            userId = currentUserId()
        )

    @Transactional(readOnly = true)
    fun findByBarCode(barCode: String): List<Product> =
        productRepository.findProductByBarCodeAndUserId(
            barCode = barCode,
            userId = currentUserId()
        )

    @Transactional
    fun create(product: ProductRequest): Product {
        val product = Product(
            name = product.name,
            category = validCategory(product.categoryId),
            barCode = product.barCode,
            userId = currentUserId()
        )

        return productRepository.save(product)
    }

    @Transactional
    fun update(id: UUID, request: ProductRequest): Product {
        val product = findById(id)

        product.name = request.name
        product.barCode = request.barCode ?: product.barCode
        product.category = validCategory(request.categoryId)

        return productRepository.save(product)
    }

    @Transactional
    fun delete(id: UUID) = findById(id).let { product -> productRepository.delete(product) }
}