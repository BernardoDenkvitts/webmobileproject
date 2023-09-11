package com.horseecommerce.project.service.Product

import com.horseecommerce.project.converters.ProductConverter
import com.horseecommerce.project.dtos.ProductDTO
import com.horseecommerce.project.dtos.ProductRequestDTO
import com.horseecommerce.project.dtos.ProductResponseDTO
import com.horseecommerce.project.exceptions.BadRequestException
import com.horseecommerce.project.exceptions.InternalServerErrorException
import com.horseecommerce.project.exceptions.NotFoundException
import com.horseecommerce.project.model.Product.Product
import com.horseecommerce.project.repository.ProductRepository

import org.springframework.stereotype.Service
import org.springframework.boot.context.properties.bind.validation.ValidationErrors
import org.springframework.web.client.HttpClientErrorException.NotFound


private const val PRODUCT_NOT_FOUND_MESSAGE = "Produto não encontrado"
private const val ERROR_TO_UPDATE_PRODUCT = "Erro ao atualizar produto"
private const val ERROR_INTERNO = "Ocorreu um erro interno"

@Service
class ProductService(private val repository: ProductRepository, private val converter: ProductConverter
):IProductService
{

    override fun getAllProducts(): List<ProductResponseDTO> {
        return repository.findAll().map(converter::toProductResponseDTO)
    }

    override fun getProductByID(productID: String): ProductResponseDTO {
        val product: Product = repository.findProductByID(productID) ?: throw NotFoundException(PRODUCT_NOT_FOUND_MESSAGE)
        return converter.toProductResponseDTO(product)
    }

    override fun createProduct(dto: ProductDTO): ProductResponseDTO {
        try {
            val product = converter.toProduct(dto)
            repository.save(product)
            return converter.toProductResponseDTO(product)
        }catch (e: Exception){
            throw InternalServerErrorException(ERROR_INTERNO)
        }

    }

    override fun updateProduct(productID: String, dto: ProductRequestDTO): ProductResponseDTO {
        val prod: Product = repository.findProductByID(productID) ?: throw NotFoundException(PRODUCT_NOT_FOUND_MESSAGE)

        try {
            val updatedProd = prod.copy(
                name = dto.name ?: prod.name,
                price = dto.price ?: prod.price,
                quantity = dto.quantity ?: prod.quantity,
                type = dto.type ?: prod.type

            )
            return converter.toProductResponseDTO(repository.save(updatedProd))
        }catch (e: Exception){
            println("Erro -> ${e}")
            throw InternalServerErrorException(ERROR_TO_UPDATE_PRODUCT)
        }

    }


}