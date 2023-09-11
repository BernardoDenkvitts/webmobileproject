package com.horseecommerce.project.controller

import com.horseecommerce.project.dtos.ProductDTO
import com.horseecommerce.project.dtos.ProductResponseDTO
import com.horseecommerce.project.service.Product.ProductService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
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

    @GetMapping("/{productID}")
    public fun getProductByID(@PathVariable productID: String): ResponseEntity<ProductResponseDTO> {
        return ResponseEntity.ok(service.getProductByID(productID))
    }

    @PostMapping
    private fun createNewProduct(
        @RequestBody @Valid newProduct: ProductDTO, uriBuilder: UriComponentsBuilder): ResponseEntity<ProductResponseDTO> {
        val newProductResponse = service.createProduct(newProduct)
        val uri: URI = uriBuilder.path("/api/products/${newProductResponse.id}").build().toUri()
        return ResponseEntity.created(uri).body(newProductResponse)
    }

}