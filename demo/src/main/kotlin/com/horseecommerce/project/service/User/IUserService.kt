package com.horseecommerce.project.service.User

import com.horseecommerce.project.dtos.Product.ProductDTO
import com.horseecommerce.project.dtos.Product.ProductRequestDTO
import com.horseecommerce.project.dtos.Product.ProductResponseDTO
import com.horseecommerce.project.dtos.User.AddressRequestDTO
import com.horseecommerce.project.dtos.User.UserDTO
import com.horseecommerce.project.dtos.User.UserRequestDTO
import com.horseecommerce.project.dtos.User.UserResponseDTO
import com.horseecommerce.project.model.Product.Product

interface IUserService {

    fun getAllUsers(): List<UserResponseDTO>

    fun findUserByID(id: String): UserResponseDTO

    fun createUser(dto: UserDTO): UserResponseDTO

    fun updateUser(id: String, dto: UserRequestDTO): UserResponseDTO

    fun addToProductList(id: String, dto: Product): UserResponseDTO

    fun removeFromProductList(id: String, productID: String): UserResponseDTO

    fun delete(id: String)

}