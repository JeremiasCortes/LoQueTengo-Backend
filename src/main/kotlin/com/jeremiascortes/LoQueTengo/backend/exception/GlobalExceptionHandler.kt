package com.jeremiascortes.LoQueTengo.backend.exception

import com.jeremiascortes.LoQueTengo.backend.dto.response.ApiResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * Manejador global de excepciones. Convierte excepciones de dominio a
 * respuestas HTTP coherentes, devolviendo siempre el wrapper
 * [ApiResponse] para mantener un contrato uniforme con el cliente.
 */
@RestControllerAdvice
class GlobalExceptionHandler {

    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleNotFound(ex: ResourceNotFoundException): ResponseEntity<ApiResponse<Nothing>> =
        ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ApiResponse.error(ex.message ?: "Recurso no encontrado"))

    @ExceptionHandler(EmailAlreadyExistsException::class)
    fun handleEmailConflict(ex: EmailAlreadyExistsException): ResponseEntity<ApiResponse<Nothing>> =
        ResponseEntity.status(HttpStatus.CONFLICT)
            .body(ApiResponse.error(ex.message))

    @ExceptionHandler(OAuthAccountAlreadyLinkedException::class)
    fun handleOAuthConflict(ex: OAuthAccountAlreadyLinkedException): ResponseEntity<ApiResponse<Nothing>> =
        ResponseEntity.status(HttpStatus.CONFLICT)
            .body(ApiResponse.error(ex.message))

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(ex: IllegalArgumentException): ResponseEntity<ApiResponse<Nothing>> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse.error(ex.message ?: "Solicitud inválida"))

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(ex: MethodArgumentNotValidException): ResponseEntity<ApiResponse<Nothing>> {
        val errors = ex.bindingResult.fieldErrors
            .joinToString(", ") { "${it.field}: ${it.defaultMessage}" }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse.error(errors))
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleMalformedBody(ex: HttpMessageNotReadableException): ResponseEntity<ApiResponse<Nothing>> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse.error("El cuerpo de la solicitud es inválido o está vacío"))

    @ExceptionHandler(Exception::class)
    fun handleGeneral(ex: Exception): ResponseEntity<ApiResponse<Nothing>> {
        // Log SIEMPRE los 500: si no, no sabrás nunca qué pasó en producción.
        log.error("Unhandled exception", ex)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ApiResponse.error("Error interno del servidor"))
    }
}