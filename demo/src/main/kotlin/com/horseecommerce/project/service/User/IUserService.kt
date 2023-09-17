package com.horseecommerce.project.service.User

import com.horseecommerce.project.dtos.Product.ProductDTO
import com.horseecommerce.project.dtos.Product.ProductRequestDTO
import com.horseecommerce.project.dtos.Product.ProductResponseDTO
import com.horseecommerce.project.dtos.User.UserDTO
import com.horseecommerce.project.dtos.User.UserRequestDTO
import com.horseecommerce.project.dtos.User.UserResponseDTO
import com.horseecommerce.project.model.User.User

interface IUserService {

    fun findUserByID(id: String): UserResponseDTO

    fun createUser(dto: UserDTO): UserResponseDTO

    fun updateUser(id: String, dto: UserRequestDTO): UserResponseDTO

    fun delete(id: String)
}