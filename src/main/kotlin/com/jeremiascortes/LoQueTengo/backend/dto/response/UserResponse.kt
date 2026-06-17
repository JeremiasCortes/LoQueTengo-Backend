package com.jeremiascortes.LoQueTengo.backend.dto.response

import com.jeremiascortes.LoQueTengo.backend.entity.User
import java.util.*

/**
 * Response con datos del usuario
 */
data class UserResponse(
    val id: UUID?,
    val email: String?
) {
    companion object {
        fun fromEntity(user: User): UserResponse = UserResponse(
            id = user.id,
            email =user.email
        )
    }
}