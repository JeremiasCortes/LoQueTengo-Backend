package com.jeremiascortes.LoQueTengo.backend.controller

import com.jeremiascortes.LoQueTengo.backend.dto.request.CategoryRequest
import com.jeremiascortes.LoQueTengo.backend.dto.response.ApiResponse
import com.jeremiascortes.LoQueTengo.backend.dto.response.CategoryResponse
import com.jeremiascortes.LoQueTengo.backend.service.CategoryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/category")
class CategoryController(
    private val categoryService: CategoryService
) {

    /**
     * Recupera todas las categorías disponibles y las devuelve en un formato estructurado.
     *
     * @return una ResponseEntity que contiene un ApiResponse con una lista de CategoryResponse.
     * La respuesta incluye todas las categorías disponibles en el sistema en un formato procesado.
     */
    @GetMapping
    fun getAll(): ResponseEntity<ApiResponse<List<CategoryResponse>>> =
        ResponseEntity.ok(
            ApiResponse.success(
                categoryService.findAll().map { CategoryResponse.fromEntity(it) }
            )
        )

    /**
     * Obtiene una categoría por su identificador único.
     *
     * @param id El identificador único de la categoría que se desea obtener.
     * @return Una respuesta HTTP (`ResponseEntity`) que contiene un `ApiResponse` con la información de la categoría
     * representada como `CategoryResponse`.
     */
    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<ApiResponse<CategoryResponse>> =
        ResponseEntity.ok(
            ApiResponse.success(
                CategoryResponse.fromEntity(categoryService.findById(id))
            )
        )

    /**
     * Obtiene una categoría por su nombre.
     *
     * @param name Nombre de la categoría a buscar.
     * @return ResponseEntity que contiene un ApiResponse con los datos de la categoría encontrada.
     *         En caso de no encontrar la categoría, se lanza una excepción.
     */
    @GetMapping("/search")
    fun getByName(@RequestParam name: String): ResponseEntity<ApiResponse<CategoryResponse>> =
        ResponseEntity.ok(
            ApiResponse.success(
                CategoryResponse.fromEntity(categoryService.findByName(name))
            )
        )

    /**
     * Crea una nueva categoría basada en los datos proporcionados en la solicitud.
     *
     * @param request Objeto de solicitud que contiene los datos validados necesarios para crear la nueva categoría.
     * @return Una respuesta HTTP (`ResponseEntity`) que envuelve un `ApiResponse` con la información de la
     * categoría creada representada como `CategoryResponse`.
     */
    @PostMapping
    fun create(@RequestBody @Valid request: CategoryRequest): ResponseEntity<ApiResponse<CategoryResponse>> {
        val category = categoryService.create(request)

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(CategoryResponse.fromEntity(category)))
    }

    /**
     * Actualiza una categoría existente en base al identificador proporcionado y los datos incluidos en la solicitud.
     *
     * @param id Identificador único de la categoría que se desea actualizar.
     * @param request Objeto de solicitud que contiene los datos validados para la actualización de la categoría.
     * @return ResponseEntity que envuelve un ApiResponse con la información de la categoría actualizada.
     */
    @PutMapping("/{id}")
    fun update(
        @PathVariable id: UUID,
        @RequestBody @Valid request: CategoryRequest
    ): ResponseEntity<ApiResponse<CategoryResponse>> {
        val category = categoryService.update(
            id = id,
            request = request,
        )

        return ResponseEntity.ok(
            ApiResponse.success(
                CategoryResponse.fromEntity(category)
            )
        )
    }

    /**
     * Elimina una categoría existente basada en su identificador único.
     *
     * @param id Identificador único de la categoría que se desea eliminar.
     * @return Una respuesta HTTP (`ResponseEntity`) que envuelve un `ApiResponse`. El `ApiResponse` confirma
     *         el éxito de la operación y devuelve un mensaje indicando que la categoría ha sido eliminada.
     */
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<ApiResponse<Unit>> {
        categoryService.delete(id)
        return ResponseEntity.ok(
            ApiResponse.success(Unit, "Categoría eliminada")
        )
    }
}