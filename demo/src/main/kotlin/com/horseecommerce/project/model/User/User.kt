package com.horseecommerce.project.model.User

import com.horseecommerce.project.model.Product.Product
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("user")
data class User(
    @field:Id
    val id: String? = null,

    @field:NotBlank
    @field:Size(min = 1, max = 30)
    val first_name: String,

    @field:NotBlank
    @field:Size(min = 1, max = 45)
    val last_name: String,

    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotBlank
    @field:Size(min = 5, max = 25)
    val phone: String,

    @field:NotBlank
    @field:Size(min = 5, max = 15)
    val password: String,

    @field:Valid    // Valida os campos dentro da classe Address (sem essa anotacao nao funciona)
    val address: Address,

    val products: MutableList<Product> = mutableListOf()
)

