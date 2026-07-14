package com.jeremiascortes.LoQueTengo.backend.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "category")
class Category (

    @Column(nullable = false, length = 32)
    val name: String

) : BaseEntity()