package com.jeremiascortes.LoQueTengo.backend.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// Endpoints de gestión del recurso Usuario bajo /api/v1/users.
//
// El registro de usuarios locales vive en AuthController bajo
// /api/v1/auth/register porque es un flujo de autenticación, no de
// gestión de recurso.
//
// Aquí irán:
//   - GET /me  (perfil del usuario autenticado, Fase 3 con JWT)
//   - GET /{id} (búsqueda, etc.)
@RestController
@RequestMapping("/api/v1/users")
class UserController
