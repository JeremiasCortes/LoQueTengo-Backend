package com.jeremiascortes.LoQueTengo.backend.dto.request

import java.util.*

data class ProductPatchRequest(
    var name: String? = null,
    var categoryId: UUID? = null,
    var barCode: String? = null
)