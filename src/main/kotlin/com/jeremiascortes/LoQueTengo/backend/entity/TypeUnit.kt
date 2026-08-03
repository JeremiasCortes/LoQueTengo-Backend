package com.jeremiascortes.LoQueTengo.backend.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import java.util.UUID

@Entity
@Table(name = "type_unit")
@SQLDelete(sql = "UPDATE type_unit SET is_deleted = TRUE, deleted_at = now(), updated_at = now() WHERE id = ?")
@SQLRestriction("is_deleted = false")
class TypeUnit(

    @Column(nullable = false, length = 32)
    var name: String,

    @Column(nullable = false, name = "user_id")
    val userId: UUID
) : BaseEntity()