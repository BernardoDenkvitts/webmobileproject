package com.horseecommerce.project.service.Product

import com.horseecommerce.project.dtos.Product.ProductDTO
import com.horseecommerce.project.dtos.Product.ProductRequestDTO
import com.horseecommerce.project.dtos.Product.ProductResponseDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface IProductService {

    fun getAllProducts(productName: String?, pageable: Pageable): Page<ProductResponseDTO>
    fun getProductByID(id: String): ProductResponseDTO
    fun createProduct(dto: ProductDTO): ProductResponseDTO
    fun updateProduct(id: String, dto: ProductRequestDTO): ProductResponseDTO

    fun delete(id: String)
}