# Changelog

Todos los cambios notables de este proyecto se documentan en este archivo.

El formato está basado en [Keep a Changelog](https://keepachangelog.com/es-ES/1.1.0/),
y este proyecto sigue [Versionado Semántico](https://semver.org/lang/es/).

## [Sin publicar]

## [0.0.3]

### Added
- CRUD de `TypeUnit`
- CRUD de `Purchase`

### Changed
- Entorno de desarrollo en sistemas Linux

## [0.0.2] - 2026-07-26

### Added
- Entidad [Category](src/main/kotlin/com/jeremiascortes/LoQueTengo/backend/entity/Category.kt)
- Entidad [Product](src/main/kotlin/com/jeremiascortes/LoQueTengo/backend/entity/Product.kt)
- Entidad [Purchase](src/main/kotlin/com/jeremiascortes/LoQueTengo/backend/entity/Purchase.kt)
- Entidad [PurchaseDetail](src/main/kotlin/com/jeremiascortes/LoQueTengo/backend/entity/PurchaseDetail.kt)
- Entidad [TypeUni](src/main/kotlin/com/jeremiascortes/LoQueTengo/backend/entity/TypeUnit.kt)
- Entidad [Unit](src/main/kotlin/com/jeremiascortes/LoQueTengo/backend/entity/Unit.kt)
- Repositorio [Category](src/main/kotlin/com/jeremiascortes/LoQueTengo/backend/repository/CategoryRepository.kt) para la entidad category
- Repositorio [Product](src/main/kotlin/com/jeremiascortes/LoQueTengo/backend/repository/ProductRepository.kt) para la entidad product
- Repositorio [Purchase](src/main/kotlin/com/jeremiascortes/LoQueTengo/backend/repository/PurchaseRepository.kt) para la entidad Purchase
- Repositorio [PurchaseDetail](src/main/kotlin/com/jeremiascortes/LoQueTengo/backend/repository/PurchaseDetailRepository.kt) para la entidad PurchaseDetail
- Repositorio [TypeUnit](src/main/kotlin/com/jeremiascortes/LoQueTengo/backend/repository/TypeUnitRepository.kt) para la entidad TypeUnit
- Repositorio [Unit](src/main/kotlin/com/jeremiascortes/LoQueTengo/backend/repository/UnitRepository.kt) para la entidad Unit
- Servicio [Category](src/main/kotlin/com/jeremiascortes/LoQueTengo/backend/service/CategoryService.kt)
- Controlador [Category](src/main/kotlin/com/jeremiascortes/LoQueTengo/backend/controller/CategoryController.kt)

## [0.0.1] - 2026-07-03

### Added

- Iniciación del sistema de JWT
- Creación de usuarios
- Login con usuarios
- Seguridad "RLS"

## [0.0.0] - 2026-06-06

### Added
- Inicialización de todo el proyecto y subida a GitHub