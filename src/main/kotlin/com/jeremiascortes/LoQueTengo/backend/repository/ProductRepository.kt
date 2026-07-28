package com.jeremiascortes.LoQueTengo.backend.repository

import com.jeremiascortes.LoQueTengo.backend.entity.Category
import com.jeremiascortes.LoQueTengo.backend.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProductRepository: JpaRepository<Product, UUID> {

    /**
     * Busca un producto en la base de datos por su identificador único.
     *
     * @param id El identificador único (UUID) del producto que se desea buscar.
     * @return El objeto [Product] que coincide con el identificador proporcionado,
     * o `null` si no se encuentra ningún producto con dicho identificador.
     */
    @Query("SELECT p FROM Product p WHERE p.id = :id")
    fun findProductById(@Param("id") id: UUID): Product?

    /**
     * Busca un producto en la base de datos por su nombre.
     *
     * @param name El nombre del producto que se desea buscar.
     * @return Un objeto [Product] que coincide con el nombre proporcionado,
     * o `null` si no se encuentra ningún producto con dicho nombre.
     */
    @Query("SELECT p FROM Product p WHERE p.name = :name")
    fun findProductName(@Param("name") name: String): Product?


    /**
     * Busca un producto en la base de datos basado en la categoría proporcionada.
     *
     * @param categoryId El identificador único de la categoría asociada al producto.
     * @return El objeto [Product] que pertenece a la categoría especificada,
     * o `null` si no se encuentra ningún producto relacionado con dicha categoría.
     */
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    fun findProductCategory(@Param("category") categoryId: String): Product?

    /**
     * Busca un producto en la base de datos que corresponda a una categoría específica.
     *
     * @param category La categoría del producto que se desea buscar.
     * @return El objeto [Product] que pertenece a la categoría especificada,
     * o `null` si no se encuentra ningún producto que coincida.
     */
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    fun findProductCategory(@Param("category") category: Category): Product?

    /**
     * Busca una lista de productos en la base de datos mediante el código de barras proporcionado.
     *
     * @param barCode El código de barras del producto que se desea buscar.
     * @return Una lista de objetos [Product] que coinciden con el código de barras especificado.
     * La lista puede estar vacía si no se encuentran productos con dicho código de barras.
     */
    @Query("SELECT p FROM Product p WHERE p.barCode = :barCode")
    fun findProductByBarCode(@Param("barCode") barCode: String): List<Product>
}