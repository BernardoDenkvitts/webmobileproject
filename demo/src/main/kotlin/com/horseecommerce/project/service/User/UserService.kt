package com.horseecommerce.project.service.User

import com.horseecommerce.project.converters.UserConverter
import com.horseecommerce.project.dtos.User.UserDTO
import com.horseecommerce.project.dtos.User.UserRequestDTO
import com.horseecommerce.project.dtos.User.UserResponseDTO
import com.horseecommerce.project.exceptions.ThrowException
import com.horseecommerce.project.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

private const val USER_NOT_FOUND_MESSAGE = "User not found"
private const val ERROR_TO_UPDATE_USER = "Error to update the user"
private const val ERROR_INTERNO = "Internal Error"

@Service
class UserService(
    private val repository: UserRepository,
    private val converter: UserConverter
): IUserService {

    override fun findUserByID(id: String): UserResponseDTO {
        val user = repository.findByIdOrNull(id) ?: throw ThrowException(USER_NOT_FOUND_MESSAGE)
        return converter.toUserResponseDTO(user)
    }

    override fun createUser(dto: UserDTO): UserResponseDTO {
        TODO("Not yet implemented")
    }

    override fun updateUser(id: String, dto: UserRequestDTO): UserResponseDTO {
        TODO("Not yet implemented")
    }

    override fun delete(id: String) {
        TODO("Not yet implemented")
    }

}