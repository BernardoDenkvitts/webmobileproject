package com.horseecommerce.project.dtos.Product

import com.horseecommerce.project.model.Product.TypeProduct

class ProductResponseDTO(
    val id: String,
    val name: String,
    val price: Int,
    val type: TypeProduct,
    val quantity: Int
)
