package com.jeremiascortes.LoQueTengo.backend.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "unit")
class Unit (

    @Column(nullable = false, length = 32)
    val name: String,

    @Column(nullable = false, length = 8)
    val abbreviation: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type", nullable = false)
    val typeUnit: TypeUnit

) : BaseEntity()