package com.horseecommerce.project.service

import com.horseecommerce.project.model.Product.Product

interface IProductService {

    fun getAllProducts(): List<Product>

    fun getProductByID(productID: String): Product

    fun getProductQuantity(productID: String): Int
}