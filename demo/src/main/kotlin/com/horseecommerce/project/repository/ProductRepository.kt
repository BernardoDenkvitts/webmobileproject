package com.horseecommerce.project.repository

import com.horseecommerce.project.model.Product.Product
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: MongoRepository<Product, Long> {

}