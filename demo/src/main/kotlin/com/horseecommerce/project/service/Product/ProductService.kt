package com.horseecommerce.project.service.Product

import com.horseecommerce.project.converters.ProductConverter
import com.horseecommerce.project.dtos.Product.ProductDTO
import com.horseecommerce.project.dtos.Product.ProductRequestDTO
import com.horseecommerce.project.dtos.Product.ProductResponseDTO
import com.horseecommerce.project.exceptions.BadRequestException
import com.horseecommerce.project.exceptions.NotFoundException
import com.horseecommerce.project.model.Product.Product
import com.horseecommerce.project.repository.ProductRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service


private const val PRODUCT_NOT_FOUND_MESSAGE = "Product not found"

@Service
class ProductService(private val repository: ProductRepository, private val converter: ProductConverter
):IProductService
{

    override fun getAllProducts(productName: String?, pageable: Pageable): Page<ProductResponseDTO> {
        val products = if(productName == null) repository.findAll(pageable)
                        else repository.findByName(productName, pageable)
        return products.map(converter::toProductResponseDTO)
    }

    private fun findProductByID(id: String): Product? {
        return repository.findAll().firstOrNull {it.id == id}
    }

    override fun getProductByID(id: String): ProductResponseDTO {
        val product: Product = findProductByID(id) ?: throw NotFoundException(PRODUCT_NOT_FOUND_MESSAGE)
        return converter.toProductResponseDTO(product)
    }

    override fun createProduct(dto: ProductDTO): ProductResponseDTO {
        val product = converter.toProduct(dto)
        repository.save(product)
        return converter.toProductResponseDTO(product)

    }

    override fun updateProduct(id: String, dto: ProductRequestDTO): ProductResponseDTO {
        val prod: Product = findProductByID(id) ?: throw NotFoundException(PRODUCT_NOT_FOUND_MESSAGE)
        val errorList = HashMap<String, String>()

        if(dto.name != null && (dto.name.isEmpty() || dto.name.length > 100))
            errorList["name"] = "name length must be between 1 and 100"

        if(dto.price != null && dto.price < 100)
            errorList["price"] = "price must be greater than 100"

        if(dto.quantity != null && dto.quantity!! < 0)
            errorList["quantity"] = "quantity must be at least 0"

        if(errorList.isNotEmpty())
            throw BadRequestException(errorList.toString())

        val updatedProd = prod.copy(
            name = dto.name ?: prod.name,
            price = dto.price ?: prod.price,
            quantity = dto.quantity ?: prod.quantity,
            type = dto.type ?: prod.type

        )
        return converter.toProductResponseDTO(repository.save(updatedProd))
    }

    override fun delete(id: String) {
        findProductByID(id) ?: throw NotFoundException(PRODUCT_NOT_FOUND_MESSAGE)
        repository.deleteById(id)
    }

}