package com.jeremiascortes.LoQueTengo.backend.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import java.util.*

@Entity
@Table(name = "category")
@SQLDelete(sql = "UPDATE category SET is_deleted = TRUE, deleted_at = now(), updated_at = now() WHERE id = ?")
@SQLRestriction("is_deleted = false")
class Category(

    @Column(nullable = false, length = 32)
    var name: String,

    @Column(nullable = false, name = "user_id")
    var user: UUID
) : BaseEntity()