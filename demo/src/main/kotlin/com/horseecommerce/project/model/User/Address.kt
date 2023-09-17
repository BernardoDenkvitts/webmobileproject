package com.horseecommerce.project.model.User

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.data.mongodb.core.mapping.Field
import javax.persistence.Embeddable

@Embeddable
data class Address(
    @Field("city")
    @NotBlank
    @Size(min = 3, max = 35)
    val city: String,

    @Field("street")
    @NotBlank
    @Size(min = 3, max = 35)
    val street: String,

    @Field("st_num")
    @NotNull
    @Size(min = 0, max = 10000)
    val st_num: Int
)
