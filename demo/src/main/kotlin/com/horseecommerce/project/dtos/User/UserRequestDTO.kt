package com.horseecommerce.project.dtos.User

data class UserRequestDTO(
    val first_name: String?,
    val last_name: String?,
    val email: String?,
    val phone: String?,
    val password: String?,
    val address: AddressRequestDTO
)
