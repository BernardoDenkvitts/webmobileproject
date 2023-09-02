package com.horseecommerce.project.service.Product

import com.horseecommerce.project.model.Product.Product
import com.horseecommerce.project.model.Product.TypeProduct
import com.horseecommerce.project.repository.ProductRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody


@Service
class ProductService(private val repository: ProductRepository): IProductService {

    override fun getAllProducts(): List<Product> = repository.findAll()

    override fun getProductByID(productID: String): Product = repository.findProductByID(productID)

    override fun getProductQuantity(productID: String): Int {
        val horse = repository.findProductByID(productID)
        return horse.quantity
    }

    override fun createProduct(newProduct: Product): Product = repository.save(newProduct)

}