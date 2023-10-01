package com.horseecommerce.project.dtos.User

import com.horseecommerce.project.model.User.Address
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.data.mongodb.core.mapping.Field
import javax.persistence.Embedded

data class UserDTO(
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

    // Na hora de criar novo usuario, intencionalmente a lista de produtos dele sera inicializada como vazia
)
