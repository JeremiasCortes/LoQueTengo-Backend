package com.jeremiascortes.LoQueTengo.backend.repository

import com.jeremiascortes.LoQueTengo.backend.entity.PurchaseDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PurchaseDetailRepository : JpaRepository<PurchaseDetail, UUID> {

    /**
     * Busca un detalle de compra en la base de datos utilizando su identificador único.
     *
     * @param id El identificador único del detalle de compra que se desea buscar.
     * @return Un objeto `PurchaseDetail` correspondiente al identificador proporcionado.
     * Lanza una excepción si no se encuentra el detalle de compra.
     */
    @Query("SELECT pd FROM PurchaseDetail pd WHERE pd.id = :id")
    fun findPurchaseDetailById(@Param("id") id: UUID): PurchaseDetail

    /**
     * Busca los detalles de compra asociados a un identificador de compra específico.
     *
     * @param purchaseId El identificador único de la compra cuyos detalles se quieren encontrar.
     * @return Una lista de objetos `PurchaseDetail` asociados al identificador de compra proporcionado.
     */
    @Query("SELECT pd FROM PurchaseDetail pd WHERE pd.purchaseId = :purchaseId")
    fun findPurchaseDetailByPurchaseId(@Param("purchaseId") purchaseId: UUID): List<PurchaseDetail>
}