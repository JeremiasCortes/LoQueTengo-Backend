package com.jeremiascortes.LoQueTengo.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "profiles")
class Profile(

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    val user: User,

    @Column(nullable = false, length = 32)
    var firstName: String,

    @Column(nullable = true, length = 32)
    var lastName: String? = null

) : BaseEntity() {
    val fullName: String
        get() = if (lastName.isNullOrBlank()) firstName else "$firstName $lastName"
}