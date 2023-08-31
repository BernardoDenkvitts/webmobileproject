package com.horseecommerce.project.service

import com.horseecommerce.project.model.Product.Product
import com.horseecommerce.project.repository.ProductRepository
import org.springframework.stereotype.Service


@Service
class ProductService(private val repository: ProductRepository): IProductService {

    override fun getAllProducts(): List<Product> = repository.findAll()

    override fun getProductByID(productID: String): Product = repository.findAll().first{ it.id == productID }

    override fun getProductQuantity(productID: String): Int {
        val horse = repository.findAll().first{it.id == productID}
        return horse.quantity
    }

}