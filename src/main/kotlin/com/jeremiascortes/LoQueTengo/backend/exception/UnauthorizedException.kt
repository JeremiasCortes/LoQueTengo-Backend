package com.jeremiascortes.LoQueTengo.backend.exception

/**
 * Excepción lanzada cuando se intenta acceder a un recurso o realizar una acción
 * para la cual el usuario no tiene autorización. Mapeada a HTTP 401 Unauthorized.
 *
 * Puede ser utilizada para indicar que las credenciales proporcionadas son insuficientes
 * o no válidas para acceder al recurso o realizar la operación solicitada.
 */
class UnauthorizedException(message: String) : RuntimeException(message)