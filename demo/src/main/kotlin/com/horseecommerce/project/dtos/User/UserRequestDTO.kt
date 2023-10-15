package com.horseecommerce.project.dtos.User

import com.horseecommerce.project.model.User.UserCreditCard
import jakarta.validation.Valid

data class UserRequestDTO(
    val first_name: String?,
    val last_name: String?,
    val email: String?,
    val phone: String?,
    val password: String?,
    val address: AddressRequestDTO?,
    @field:Valid
    val creditCard: UserCreditCard?
)
