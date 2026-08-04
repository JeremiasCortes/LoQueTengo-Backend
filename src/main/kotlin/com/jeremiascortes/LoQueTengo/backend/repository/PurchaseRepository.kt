package com.jeremiascortes.LoQueTengo.backend.repository

import com.jeremiascortes.LoQueTengo.backend.entity.Purchase
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.Instant
import java.util.UUID

@Repository
interface PurchaseRepository: JpaRepository<Purchase, UUID> {

    @Query("SELECT p FROM Purchase p WHERE p.id = :id AND p.userId = :userId")
    fun findPurchaseByIdAndUserId(
        @Param("id") id: UUID,
        @Param("userId") userId: Instant
    ): List<Purchase>


    @Query("SELECT p FROM Purchase p WHERE p.date = :date AND p.userId = :userId")
    fun findPurchaseByDateAndUserId(
        @Param("date") date: Instant,
        @Param("userId") userId: Instant
    ): List<Purchase>

    @Query("SELECT p FROM Purchase p WHERE p.userId = :userId")
    fun findAllByUserId(
        @Param("userId") userId: UUID
    ): List<Purchase>
}