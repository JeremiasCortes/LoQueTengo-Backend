package com.jeremiascortes.LoQueTengo.backend.service

import com.jeremiascortes.LoQueTengo.backend.dto.request.TypeUnitRequest
import com.jeremiascortes.LoQueTengo.backend.entity.TypeUnit
import com.jeremiascortes.LoQueTengo.backend.repository.TypeUnitRepository
import com.jeremiascortes.LoQueTengo.backend.security.SecurityContext
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TypeUnitService(
    protected val typeUnitRepository: TypeUnitRepository,
    securityContext: SecurityContext
) : BaseService(securityContext) {

    /**
     * Recupera todos los objetos de tipo `TypeUnit` asociados al usuario actualmente autenticado.
     *
     * @return Una lista de instancias de `TypeUnit` correspondientes al usuario actual. Si no existen objetos asociados,
     * se devolverá una lista vacía.
     */
    fun getAll(): List<TypeUnit> = typeUnitRepository.findAllTypeUnitByUserId(currentUserId())

    /**
     * Busca un tipo de unidad específico basado en su identificador único
     * y el identificador del usuario actual.
     *
     * @param id El identificador único (UUID) del tipo de unidad.
     * @return El tipo de unidad correspondiente al identificador proporcionado.
     * @throws Exception Si no se encuentra un tipo de unidad con el identificador especificado
     *                   o si el usuario actual no tiene acceso.
     */
    fun findById(id: UUID): TypeUnit =
        typeUnitRepository.findTypeUnitByIdByUserId(
            id = id,
            userId = currentUserId()
        ) ?: throw Exception("No se ha encontrado el tipo de unidad")

    /**
     * Crea y guarda un nuevo tipo de unidad asociado al usuario actual.
     *
     * @param typeUnit Objeto que contiene la información del tipo de unidad que se va a crear.
     * @return El tipo de unidad creado y almacenado en el repositorio.
     */
    fun create(typeUnit: TypeUnitRequest): TypeUnit {
        val typeUnit = TypeUnit(
            name = typeUnit.name,
            userId = currentUserId()
        )

        return typeUnitRepository.save(typeUnit)
    }

    /**
     *
     */
    fun update(
        id: UUID,
        request: TypeUnitRequest
    ): TypeUnit {
        val typeUnit = findById(id)

        typeUnit.name = request.name

        return typeUnitRepository.save(typeUnit)
    }

    /**
     * Elimina un registro de tipo de unidad basado en su identificador único.
     *
     * @param id Identificador único del tipo de unidad que se desea eliminar.
     * Lanza una excepción si no se encuentra el registro asociado al ID proporcionado.
     */
    fun delete(id: UUID) = findById(id).let { typeUnit -> typeUnitRepository.delete(typeUnit) }
}