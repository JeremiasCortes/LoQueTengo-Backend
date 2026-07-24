package com.jeremiascortes.LoQueTengo.backend.security

import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import java.util.*

@Component
class SecurityContext {

    private companion object {
        private val USER_ID_KEY = "userId"
    }

    fun setUserId(userId: UUID) {
        RequestContextHolder.currentRequestAttributes()
            .setAttribute(USER_ID_KEY, userId, org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST)
    }

    fun getUserId(): UUID? {
        return try {
            RequestContextHolder.currentRequestAttributes()
                .getAttribute(USER_ID_KEY, org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST) as? UUID
        } catch (e: Exception) {
            null
        }
    }
}