package com.jeremiascortes.LoQueTengo.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "product")
class Product (

    @Column(nullable = false, length = 255)
    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category", nullable = false)
    var category: Category,

    @Column(nullable = false, length = 255)
    var barCode: String,

) : BaseEntity()