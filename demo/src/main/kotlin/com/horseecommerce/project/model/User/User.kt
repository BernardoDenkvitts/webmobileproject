package com.horseecommerce.project.model.User

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document("user")
data class User(
    @Id
    val id: String? = null,

    @Field("first_name")
    @NotBlank
    @Size(min = 1, max = 30)
    val first_name: String,

    @Field("last_name")
    @NotBlank
    @Size(min = 1, max = 45)
    val last_name: String,

    @Field("email")
    @NotBlank
    @Email
    val email: String,

    @Field("phone")
    @NotBlank
    @Size(min = 5, max = 25)
    val phone: String,

    @Field("password")
    @NotBlank
    @Size(min = 5, max = 15)
    val password: String,

    @Field("address")
    @NotEmpty
    val address: Address

)

