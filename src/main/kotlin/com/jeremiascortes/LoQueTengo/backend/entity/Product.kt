package com.jeremiascortes.LoQueTengo.backend.entity

import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import java.util.*

@Entity
@Table(name = "product")
@SQLDelete(sql = "UPDATE product SET is_deleted = TRUE, deleted_at = now(), updated_at = now() WHERE id = ?")
@SQLRestriction("is_deleted = false")
class Product(

    @Column(nullable = false, length = 255)
    var name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category", nullable = false)
    var category: Category,

    @Column(nullable = false, length = 255)
    var barCode: String,

    @Column(nullable = false, name = "user_id")
    val userId: UUID
) : BaseEntity()