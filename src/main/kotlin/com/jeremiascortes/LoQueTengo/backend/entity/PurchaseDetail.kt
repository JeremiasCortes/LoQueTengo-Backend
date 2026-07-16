package com.jeremiascortes.LoQueTengo.backend.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction

@Entity
@Table(name = "purchase_detail")
@SQLDelete(sql = "UPDATE purchase_detail SET is_deleted = TRUE, deleted_at = now(), updated_at = now() WHERE id = ?")
@SQLRestriction("is_deleted = false")
class PurchaseDetail(

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
    val price_total: Double

) : BaseEntity()