package com.horseecommerce.project.model.Product

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field


@Document("Horses")
data class Product(

    @Id val id: String,
    @Field("name") val name: String,
    @Field("price") val price: Double,
    @Field("type") val horseType: TypeProduct,
    @Field("quantity") val quantity: Int

)