package com.horseecommerce.project.model.Product

import jakarta.validation.constraints.*
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import jakarta.validation.constraints.NotBlank
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Document("horses")
data class Product(

    @field:Id
    var id: String? = null,

    @field:Size(min = 1, max = 30)
    @field:NotBlank
    var name: String,

    @field:Min(100)
    @field:NotNull
    var price: Int,

    @field:Enumerated(value = EnumType.STRING)
    var type: TypeProduct,

    @field:Min(0)
    @field:NotNull
    var quantity: Int

)

