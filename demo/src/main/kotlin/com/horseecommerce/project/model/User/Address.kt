package com.horseecommerce.project.model.User

import jakarta.validation.constraints.*

data class Address(
    @field:NotBlank
    @field:Size(min = 3, max = 35)
    val city: String,

    @field:NotBlank
    @field:Size(min = 3, max = 35)
    val street: String,

    @field:NotNull
    @field:Min(0)
    @field:Max(10000)
    val st_num: Int
)
