package com.horseecommerce.project.service.Product

import com.horseecommerce.project.model.Product.Product
import org.springframework.web.bind.annotation.RequestBody

interface IProductService {

    fun getAllProducts(): List<Product>

    fun getProductByID(productID: String): Product
    fun getProductQuantity(productID: String): Int
    fun createProduct(newProduct: Product): Product

}