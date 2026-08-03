package com.jeremiascortes.LoQueTengo.backend.repository

import com.jeremiascortes.LoQueTengo.backend.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository: JpaRepository<Product, UUID> {

    /**
     * Busca todos los productos asociados a un usuario específico en la base de datos.
     *
     * @param userId El identificador único (UUID) del usuario cuyos productos se desean buscar.
     * @return Una lista de objetos [Product] que representan los productos asociados al usuario proporcionado.
     * Si no se encuentran productos, la lista estará vacía.
     */
    @Query("SELECT p FROM Product p WHERE p.userId = :userId")
    fun findAllByUserId(@Param("userId") userId: UUID): List<Product>

    /**
     * Busca un producto en la base de datos utilizando su identificador único y el identificador del usuario asociado.
     *
     * @param id El identificador único (UUID) del producto que se desea buscar.
     * @param userId El identificador único (UUID) del usuario propietario del producto.
     * @return El producto encontrado que coincide con el identificador y el usuario proporcionados,
     * o `null` si no se encuentra ningún producto con dichos criterios.
     */
    @Query("SELECT p FROM Product p WHERE p.id = :id AND p.userId = :userId")
    fun findProductByIdAndUserId(
        @Param("id") id: UUID,
        @Param("userId") userId: UUID
    ): Product?

    /**
     * Busca un producto en la base de datos que coincida con el nombre y el identificador de usuario proporcionado.
     *
     * @param name El nombre del producto que se desea buscar.
     * @param userId El identificador único (UUID) del usuario propietario del producto.
     * @return Un objeto [Product] que coincide con el nombre y el identificador de usuario proporcionado,
     * o `null` si no se encuentra ningún producto que cumpla con los criterios.
     */
    @Query("SELECT p FROM Product p WHERE p.name = :name AND p.userId = :userId")
    fun findProductByNameAndUserId(
        @Param("name") name: String,
        @Param("userId") userId: UUID
    ): Product?


    /**
     * Busca productos en la base de datos que pertenezcan a una categoría específica y estén asociados a un usuario determinado.
     *
     * @param categoryId El identificador único (UUID) de la categoría a la que pertenecen los productos.
     * @param userId El identificador único (UUID) del usuario propietario de los productos.
     * @return Una lista de objetos [Product] que representan los productos que coinciden con la categoría y el usuario proporcionados.
     * Si no se encuentran productos, la lista estará vacía.
     */
    @Query("SELECT p FROM Product p WHERE p.category.id = :category AND p.userId = :userId")
    fun findProductByCategoryAndUserId(
        @Param("category") categoryId: UUID,
        @Param("userId") userId: UUID
    ): List<Product>

    /**
     * Busca productos en la base de datos asociados a un usuario específico y que tengan un código de barras determinado.
     *
     * @param barCode El código de barras del producto que se desea buscar.
     * @param userId El identificador único (UUID) del usuario asociado con los productos.
     * @return Una lista de objetos [Product] que coinciden con el código de barras y el usuario proporcionados.
     * Si no se encuentran productos, la lista estará vacía.
     */
    @Query("SELECT p FROM Product p WHERE p.barCode = :barCode AND p.userId = :userId")
    fun findProductByBarCodeAndUserId(
        @Param("barCode") barCode: String,
        @Param("userId") userId: UUID
    ): List<Product>
}