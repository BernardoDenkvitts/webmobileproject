package com.horseecommerce.project.dtos.Authentication

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size


class LoginDTO(
    @field:Email
    val email: String,

    @field:Size(min = 4, max = 15)
    val password: String
)