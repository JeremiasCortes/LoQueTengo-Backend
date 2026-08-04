package com.jeremiascortes.LoQueTengo.backend.service

import com.jeremiascortes.LoQueTengo.backend.entity.Purchase
import com.jeremiascortes.LoQueTengo.backend.repository.ProductRepository
import com.jeremiascortes.LoQueTengo.backend.repository.PurchaseRepository
import com.jeremiascortes.LoQueTengo.backend.security.SecurityContext
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository,
    securityContext: SecurityContext
): BaseService(securityContext) {
    fun getAll(): List<Purchase> =
        purchaseRepository.findAllByUserId(currentUserId())

    fun getById(): Purchase = TODO()
}