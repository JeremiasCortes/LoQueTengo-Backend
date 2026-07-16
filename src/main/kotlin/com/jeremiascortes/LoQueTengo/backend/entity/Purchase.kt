package com.jeremiascortes.LoQueTengo.backend.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import java.time.Instant

@Entity
@Table(name = "purchase")
@SQLDelete(sql = "UPDATE purchase SET is_deleted = TRUE, deleted_at = now(), updated_at = now() WHERE id = ?")
@SQLRestriction("is_deleted = false")
class Purchase(

    @Column(nullable = false)
    var date: Instant,

    @Column(nullable = false, precision = 5, scale = 2)
    var total: Double
) : BaseEntity()