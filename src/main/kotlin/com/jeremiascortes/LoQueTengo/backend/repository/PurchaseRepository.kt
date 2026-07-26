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

    /**
     * Busca una lista de compras en la base de datos utilizando el identificador único proporcionado.
     *
     * @param id El identificador único de la compra que se desea buscar.
     * @return Una lista de objetos `Purchase` que coinciden con el identificador proporcionado.
     *         Si no se encuentran resultados, se devolverá una lista vacía.
     */
    @Query("SELECT p FROM Purchase p WHERE p.id = :id")
    fun findPurchaseById(@Param("id") id: UUID): List<Purchase>

    /**
     * Busca compras en la base de datos que coincidan con la fecha especificada.
     *
     * @param date La fecha de las compras que se desea buscar.
     * @return Una lista de objetos `Purchase` que coinciden con la fecha proporcionada.
     * Si no se encuentran coincidencias, la lista estará vacía.
     */
    @Query("SELECT p FROM Purchase p WHERE p.date = :date")
    fun findPurchaseByDate(@Param("date") date: Instant): List<Purchase>
}