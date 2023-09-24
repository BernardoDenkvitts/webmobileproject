package com.horseecommerce.project.dtos.Product

import com.horseecommerce.project.model.Product.TypeProduct
import jakarta.validation.constraints.Size
import jakarta.validation.constraints.*
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import javax.persistence.EnumType
import javax.persistence.Enumerated

data class ProductDTO (

    @field:NotBlank(message = "Product must have a name")
    @field:Size(min = 1, max = 30 ,message="Name must be at least 1 character long")
    val name: String,

    @field:NotNull(message = "Product must have a price")
    @field:Min(100, message = "Product must coast at least 100")
    val price: Int,

    @field:Enumerated(value = EnumType.STRING)
    val type: TypeProduct,

    @field:NotNull(message = "Product quantity can't be null")
    @field:Min(0, message = "Product quantity should be at least 0")
    var quantity: Int
)
