package com.horseecommerce.project.dtos.User

import com.horseecommerce.project.model.User.Address
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import org.springframework.data.mongodb.core.mapping.Field

data class UserDTO(
    @NotBlank
    @Size(min = 1, max = 30)
    val first_name: String,

    @NotBlank
    @Size(min = 1, max = 45)
    val last_name: String,

    @NotBlank
    @Email
    val email: String,

    @NotBlank
    @Size(min = 5, max = 25)
    val phone: String,

    @NotBlank
    @Size(min = 5, max = 15)
    val password: String,

    @NotEmpty
    val address: Address
)
