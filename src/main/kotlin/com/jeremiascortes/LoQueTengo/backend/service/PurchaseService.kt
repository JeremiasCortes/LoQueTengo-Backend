package com.jeremiascortes.LoQueTengo.backend.service

import com.jeremiascortes.LoQueTengo.backend.dto.request.PurchaseRequest
import com.jeremiascortes.LoQueTengo.backend.entity.Purchase
import com.jeremiascortes.LoQueTengo.backend.repository.PurchaseRepository
import com.jeremiascortes.LoQueTengo.backend.security.SecurityContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.util.*

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository,
    securityContext: SecurityContext
): BaseService(securityContext) {
    @Transactional(readOnly = true)
    fun getAll(): List<Purchase> =
        purchaseRepository.findAllByUserId(currentUserId())

    @Transactional(readOnly = true)
    fun getById(id: UUID): Purchase =
        purchaseRepository.findPurchaseByIdAndUserId(
            id = id,
            userId = currentUserId()
        )

    @Transactional(readOnly = true)
    fun getByDate(date: Instant): List<Purchase> =
        purchaseRepository.findPurchaseByDateAndUserId(
            date = date,
            userId = currentUserId()
        )

    @Transactional
    fun create(request: PurchaseRequest): Purchase {
        val purchase = Purchase(
            date = request.date,
            total = request.total,
            userId = currentUserId()
        )

        return purchaseRepository.save(purchase)
    }

    @Transactional
    fun update(
        request: PurchaseRequest,
        id: UUID
    ): Purchase {
        val purchase = getById(id)

        purchase.date = request.date
        purchase.total = request.total

        return purchaseRepository.save(purchase)
    }

    @Transactional
    fun delete(id: UUID) = getById(id).let { purchase -> purchaseRepository.delete(purchase) }
}