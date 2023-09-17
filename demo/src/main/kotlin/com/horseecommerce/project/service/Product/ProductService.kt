package com.horseecommerce.project.service.Product

import com.horseecommerce.project.converters.ProductConverter
import com.horseecommerce.project.dtos.Product.ProductDTO
import com.horseecommerce.project.dtos.Product.ProductRequestDTO
import com.horseecommerce.project.dtos.Product.ProductResponseDTO
import com.horseecommerce.project.exceptions.ThrowException
import com.horseecommerce.project.model.Product.Product
import com.horseecommerce.project.repository.ProductRepository

import org.springframework.stereotype.Service


private const val PRODUCT_NOT_FOUND_MESSAGE = "Product not found"
private const val ERROR_TO_UPDATE_PRODUCT = "Error to update the product"
private const val ERROR_INTERNO = "Internal Error"

@Service
class ProductService(private val repository: ProductRepository, private val converter: ProductConverter
):IProductService
{

    override fun getAllProducts(): List<ProductResponseDTO> {
        return repository.findAll().map(converter::toProductResponseDTO)
    }

    private fun findProductByID(id: String): Product? {
        return repository.findAll().firstOrNull {it.id == id}
    }

    override fun getProductByID(id: String): ProductResponseDTO {
        val product: Product = findProductByID(id) ?: throw ThrowException(PRODUCT_NOT_FOUND_MESSAGE)
        return converter.toProductResponseDTO(product)
    }

    override fun createProduct(dto: ProductDTO): ProductResponseDTO {
        val product = converter.toProduct(dto)
        repository.save(product)
        return converter.toProductResponseDTO(product)

    }

    override fun updateProduct(id: String, dto: ProductRequestDTO): ProductResponseDTO {
        val prod: Product = findProductByID(id) ?: throw ThrowException(PRODUCT_NOT_FOUND_MESSAGE)
        val updatedProd = prod.copy(
            name = dto.name ?: prod.name,
            price = dto.price ?: prod.price,
            quantity = dto.quantity ?: prod.quantity,
            type = dto.type ?: prod.type

        )
        return converter.toProductResponseDTO(repository.save(updatedProd))
    }

    override fun delete(id: String) {
        val product: Product = findProductByID(id) ?: throw ThrowException(PRODUCT_NOT_FOUND_MESSAGE)
        repository.deleteById(id)
    }

}