package com.jeremiascortes.LoQueTengo.backend.dto.response

data class ApiResponse<T>(
    val success: Boolean,
    val data: T?,
    val message: String?,
) {
    companion object {
        fun <T> success(
            data: T,
            message: String? = null
        ): ApiResponse<T> = ApiResponse(
            success = true,
            data = data,
            message = message
        )

        fun <T> error(
            message: String? = null
        ): ApiResponse<T> = ApiResponse(
            success = false,
            data = null,
            message = message
        )
    }
}