package com.horseecommerce.project.dtos

import com.horseecommerce.project.model.Product.TypeProduct
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class ProductDTO (

    @field:Size(min = 1, message = "Name must be at least 1 character long")
    @field:Size(max = 30, message = "Name length cant be longer then 30")
    @field:NotBlank(message = "Product must have a name")
    val name: String,

    @field:NotNull(message = "Product must have a price")
    @field:Min(100, message = "Product must coast at least 100")
    val price: Int,

    @field:NotBlank(message = "Product must have a type")
    @Pattern(regexp = "^Rare|Casual|Race$", message = "Product type must be 'Rare' or 'Casual' or 'Race'")
    val type: TypeProduct,

    @field:NotNull(message = "Product quantity can't be null")
    @field:Min(0, message = "Product quantity should be at least 0")
    var quantity: Int
)
