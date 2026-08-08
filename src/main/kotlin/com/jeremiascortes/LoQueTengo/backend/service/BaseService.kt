package com.jeremiascortes.LoQueTengo.backend.service

import com.jeremiascortes.LoQueTengo.backend.exception.UnauthorizedException
import com.jeremiascortes.LoQueTengo.backend.security.SecurityContext
import java.util.UUID

abstract class BaseService(
    protected val securityContext: SecurityContext
) {
    protected fun currentUserId(): UUID =
        securityContext.getUserId() ?: throw UnauthorizedException("No se pudo identificar al usuario")
}
