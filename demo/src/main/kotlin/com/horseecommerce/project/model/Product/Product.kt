package com.horseecommerce.project.model.Product

import jakarta.validation.constraints.*
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Document("horses")
data class Product(

    @Id
    var id: String? = null,

    @Field("name")
    @Size(min = 1, max = 30)
    @field:NotBlank
    var name: String,

    @Field("price")
    @Min(100)
    @NotNull
    var price: Int,

    @Field("type")
    @Size(max = 6)
    @Enumerated(value = EnumType.STRING)
    @Pattern(regexp = "^Rare|Casual|Race$")
    var type: TypeProduct,

    @Field("quantity")
    @Size(min = 0)
    @NotNull
    var quantity: Int

)

