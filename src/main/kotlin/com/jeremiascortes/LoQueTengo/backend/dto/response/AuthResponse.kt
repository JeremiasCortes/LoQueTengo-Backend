package com.jeremiascortes.LoQueTengo.backend.dto.response

import com.jeremiascortes.LoQueTengo.backend.entity.User

class AuthResponse(
    val token: String,
    val user: UserResponse
) {
    companion object {
        fun from(token: String, user: User): AuthResponse = AuthResponse(
            token = token,
            user = UserResponse.fromEntity(user)
        )
    }
}