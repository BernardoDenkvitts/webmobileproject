package com.horseecommerce.project.converters

import com.horseecommerce.project.dtos.ProductDTO
import com.horseecommerce.project.dtos.ProductRequestDTO
import com.horseecommerce.project.dtos.ProductResponseDTO
import com.horseecommerce.project.model.Product.Product
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
class ProductConverter {

    fun toProduct(dto: ProductDTO): Product {
        return Product(
            name = dto.name,
            price = dto.price,
            type = dto.type,
            quantity = dto.quantity
        )
    }

    fun toProductResponseDTO(product: Product): ProductResponseDTO {
        return ProductResponseDTO(
            id = product.id!! ,//?: "-",
            name = product.name,
            price = product.price,
            type = product.type,
            quantity = product.quantity
        )
    }

}