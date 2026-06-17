package com.jeremiascortes.LoQueTengo.backend.entity

import jakarta.persistence.*
import java.time.Instant
import java.util.*

/**
 * Clase base abstracta para todas las entidades.
 * Proporciona campso comunes para sincronización multi-dispositivo.
 * @author Jeremías Cortés
 */
@MappedSuperclass
abstract class BaseEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID? = null,

    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: Instant = Instant.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant? = Instant.now(),

    @Column(name = "deleted_at")
    var deletedAt: Instant? = null,

    @Column(name = "is_deleted", nullable = false)
    var isDeleted: Boolean = false
) {
    /**
     * Marca el registro como eliminado
     */
    open fun softDelete() {
        isDeleted = true
        deletedAt = Instant.now()
        updatedAt = Instant.now()
    }

    /**
     * Restaura el registro eliminado
     */
    open fun restore() {
        isDeleted = false
        deletedAt = null
        updatedAt = Instant.now()
    }

    /**
     * Actualiza el campo updated_at
     */
    open fun update() {
        updatedAt = Instant.now()
    }

    /**
     * Verifica si el registro está eliminado
     */
    open fun isAlive(): Boolean = !isDeleted
}