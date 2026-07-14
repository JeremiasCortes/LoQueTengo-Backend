package com.jeremiascortes.LoQueTengo.backend.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "purchase")
class Purchase (

    @Column(nullable = false)
    var date: Instant,

    @Column(nullable = false, precision = 5, scale = 2)
    var total: Double,

) : BaseEntity()