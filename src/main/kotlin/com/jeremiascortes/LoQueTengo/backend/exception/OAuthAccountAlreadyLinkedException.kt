package com.jeremiascortes.LoQueTengo.backend.exception

import com.jeremiascortes.LoQueTengo.backend.entity.AuthProvider

/**
 * Lanzada cuando se intenta vincular una cuenta OAuth (provider + providerId)
 * que ya pertenece a otro usuario. Mapeada a HTTP 409 Conflict en
 * [GlobalExceptionHandler].
 */
class OAuthAccountAlreadyLinkedException(provider: AuthProvider) :
    RuntimeException("Ya existe una cuenta vinculada a ${provider.name}")