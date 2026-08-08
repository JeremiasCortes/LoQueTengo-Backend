package com.jeremiascortes.LoQueTengo.backend.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "unit")
class Unit (

    @Column(nullable = false, length = 32)
    var name: String,

    @Column(nullable = false, length = 8)
    var abbreviation: String,

    @Column(name = "type_unit_id", nullable = false)
    var typeUnitId: UUID,

    @Column(name = "user_id", nullable = false)
    val userId: UUID

) : BaseEntity()