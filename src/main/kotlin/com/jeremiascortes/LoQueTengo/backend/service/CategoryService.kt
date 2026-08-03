package com.jeremiascortes.LoQueTengo.backend.service

import com.jeremiascortes.LoQueTengo.backend.dto.request.CategoryRequest
import com.jeremiascortes.LoQueTengo.backend.entity.Category
import com.jeremiascortes.LoQueTengo.backend.exception.ResourceNotFoundException
import com.jeremiascortes.LoQueTengo.backend.repository.CategoryRepository
import com.jeremiascortes.LoQueTengo.backend.security.SecurityContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
    securityContext: SecurityContext
) : BaseService(securityContext) {
    /**
     * Recupera todas las categorías asociadas al usuario actualmente autenticado.
     *
     * @return Una lista de objetos [Category] que representan las categorías del usuario.
     * Si el usuario no tiene categorías asociadas, se devolverá una lista vacía.
     */
    @Transactional(readOnly = true)
    fun findAll(): List<Category> = categoryRepository.findAllByUserId(currentUserId())

    /**
     * Busca una categoría por su identificador y el identificador del usuario asociado.
     *
     * @param id El identificador único (UUID) de la categoría que se desea buscar.
     * @return La categoría encontrada que corresponde al identificador proporcionado y pertenece al usuario autenticado.
     * @throws ResourceNotFoundException Si no se encuentra ninguna categoría que corresponda al identificador proporcionado.
     */
    @Transactional(readOnly = true)
        fun findById(id: UUID): Category =
        categoryRepository.findCategoryByIdAndUserId(
            userId = currentUserId(),
            id = id
        ) ?: throw ResourceNotFoundException("Categoria no encontrada")

    /**
     * Busca una categoría por su nombre asociada al usuario actual.
     *
     * @param name El nombre de la categoría que se desea buscar.
     * @return La categoría que coincide con el nombre proporcionado y está asociada al usuario actual.
     * @throws ResourceNotFoundException Si no se encuentra ninguna categoría que coincida con los criterios dados.
     */
    @Transactional(readOnly = true)
    fun findByName(name: String): Category =
        categoryRepository.findCategoryByNameAndUserId(
            userId = currentUserId(),
            name = name
        ) ?: throw ResourceNotFoundException("Categoria no encontrada")

    /**
     * Crea una nueva categoría asociada al usuario autenticado en el contexto de seguridad.
     *
     * @param request Objeto que contiene los datos necesarios para crear la categoría, incluido el nombre.
     * @return La categoría creada con los valores establecidos, incluyendo el usuario asociado.
     */
    @Transactional()
    fun create(request: CategoryRequest): Category {
        val userId = currentUserId()

        val category = Category(
            name = request.name,
            user = userId
        )

        return categoryRepository.save(category)
    }

    /**
     * Actualiza una categoría existente con nuevos valores proporcionados en la solicitud.
     *
     * @param id El identificador único (UUID) de la categoría que se desea actualizar.
     * @param request Objeto [CategoryRequest] que contiene los datos actualizados, como el nombre de la categoría.
     * @return La categoría actualizada con los nuevos valores aplicados.
     */
    @Transactional
    fun update(id: UUID, request: CategoryRequest): Category {
        val category = this.findById(id)

        request.name?.let { category.name = it }

        return categoryRepository.save(category)
    }

    /**
     * Elimina una categoría a partir de su identificador único.
     *
     * @param id El identificador único (UUID) de la categoría que se desea eliminar.
     */
    @Transactional
    fun delete(id: UUID) {
        val category = this.findById(id)

        categoryRepository.deleteById(id)
    }
}