package com.horseecommerce.project.controller

import com.horseecommerce.project.model.Product.Product
import com.horseecommerce.project.service.Product.ProductService
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.lang.RuntimeException
import java.net.http.HttpResponse

@RestController
@RequestMapping("/api/products")
class ProductController(val service: ProductService) {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @GetMapping
    public fun getAllProducts(): List<Product> = service.getAllProducts()

    @GetMapping("/{productID}")
    public fun getProductByID(@PathVariable productID: String) = service.getProductByID(productID)

    @GetMapping("/{productID}/quantity")
    public fun getProductQuantity(@PathVariable productID: String): ResponseEntity<String> {
        try {
            val data = service.getProductQuantity(productID)
            return ResponseEntity.status(HttpStatus.OK).body("${data}")
        }catch (e: Exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 Not Found")
        }
    }

    @PostMapping
    public fun createNewProduct(@RequestBody newProduct: Product): ResponseEntity<String> {

        try {
            if(newProduct.id != null)
                newProduct.id = null
            service.createProduct(newProduct)
            return ResponseEntity.status(HttpStatus.CREATED).body("Product created")

        }catch (e: Exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error to create")
        }

    }
}