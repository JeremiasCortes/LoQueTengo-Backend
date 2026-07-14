package com.jeremiascortes.LoQueTengo.backend.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "purchase_detail")
class PurchaseDetail (

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id", nullable = false)
    val purchaseId: Purchase,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    val productId: Product,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id", nullable = false)
    val unitId: Unit,

    @Column(nullable = false, precision = 5, scale = 2)
    val quantity: Double,

    @Column(nullable = false, precision = 5, scale = 2)
    val price_unit: Double,

    @Column(nullable = false, precision = 5, scale = 2)
    val price_total: Double,

) : BaseEntity()