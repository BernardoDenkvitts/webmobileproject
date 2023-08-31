package com.horseecommerce.project.controller

import com.horseecommerce.project.model.Product.Product
import com.horseecommerce.project.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/products")
class ProductController(val service: ProductService) {

    @GetMapping
    public fun getAllProducts(): List<Product> = service.getAllProducts()

    @GetMapping("/{productID}")
    public fun getProductByID(@PathVariable productID: String) = service.getProductByID(productID)

    @GetMapping("/{productID}/quantity")
    public fun getProductQuantity(@PathVariable productID: String) = service.getProductQuantity(productID)

}