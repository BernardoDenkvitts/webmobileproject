package com.horseecommerce.project.dtos

import com.horseecommerce.project.model.Product.TypeProduct

data class ProductRequestDTO (
    val name: String?,
    val price: Int?,
    val type: TypeProduct?,
    var quantity: Int?
)