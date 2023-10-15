package com.horseecommerce.project.dtos.User

import com.horseecommerce.project.model.Product.Product
import com.horseecommerce.project.model.User.Address
import com.horseecommerce.project.model.User.UserCreditCard

data class UserResponseDTO(
    val id: String,
    val first_name: String,
    val last_name: String,
    val email: String,
    val phone: String,
    val address: Address?,
    val creditCards: Set<UserCreditCard>,
    val products: List<Product>
)
