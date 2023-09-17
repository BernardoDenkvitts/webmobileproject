package com.horseecommerce.project.controller

import com.horseecommerce.project.dtos.Product.ProductDTO
import com.horseecommerce.project.dtos.Product.ProductRequestDTO
import com.horseecommerce.project.dtos.Product.ProductResponseDTO
import com.horseecommerce.project.service.Product.ProductService

import jakarta.validation.Valid
import org.springframework.http.HttpStatus

import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

import java.net.URI


@RestController
@RequestMapping("/api/products")
class ProductController(private val service: ProductService) {

    @GetMapping
    public fun getAllProducts(): ResponseEntity<List<ProductResponseDTO>> {
        return ResponseEntity.ok(service.getAllProducts())
    }

    @GetMapping("/{id}")
    public fun getProductByID(@PathVariable id: String): ResponseEntity<ProductResponseDTO> {
        return ResponseEntity.ok(service.getProductByID(id))
    }

    @PostMapping
    @Transactional
    private fun createNewProduct(
        @RequestBody @Valid newProduct: ProductDTO, uriBuilder: UriComponentsBuilder
    ): ResponseEntity<ProductResponseDTO> {
        val newProductResponse = service.createProduct(newProduct)
        val uri: URI = uriBuilder.path("/api/products/${newProductResponse.id}").build().toUri()
        return ResponseEntity.created(uri).body(newProductResponse)
    }

    @PutMapping("/{id}")
    @Transactional
    private fun updateProduct(@PathVariable id: String, @RequestBody dto: ProductRequestDTO): ResponseEntity<ProductResponseDTO> {
        return ResponseEntity.ok(service.updateProduct(id, dto))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    private fun deleteProduct(@PathVariable id: String){
        service.delete(id)
    }

}