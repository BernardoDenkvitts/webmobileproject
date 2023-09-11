package com.horseecommerce.project.service.Product

import com.horseecommerce.project.dtos.ProductDTO
import com.horseecommerce.project.dtos.ProductRequestDTO
import com.horseecommerce.project.dtos.ProductResponseDTO
import com.horseecommerce.project.model.Product.Product

interface IProductService {

    fun getAllProducts(): List<ProductResponseDTO>
    fun getProductByID(productID: String): ProductResponseDTO
    fun createProduct(newProduct: ProductDTO): ProductResponseDTO
    fun updateProduct(id: String, dto: ProductRequestDTO): ProductResponseDTO

}