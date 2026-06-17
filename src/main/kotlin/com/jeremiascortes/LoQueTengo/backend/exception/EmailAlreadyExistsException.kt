package com.jeremiascortes.LoQueTengo.backend.exception

/**
 * Lanzada cuando se intenta registrar un usuario con un email que ya está
 * asociado a una cuenta activa. Mapeada a HTTP 409 Conflict en
 * [GlobalExceptionHandler].
 */
class EmailAlreadyExistsException(email: String) :
    RuntimeException("El email $email ya está registrado")