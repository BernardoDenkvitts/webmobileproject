package com.horseecommerce.project.converters

import com.horseecommerce.project.dtos.User.UserDTO
import com.horseecommerce.project.dtos.User.UserResponseDTO
import com.horseecommerce.project.model.User.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserConverter {

    fun toUser(dto: UserDTO): User{
        return User(
            first_name =  dto.first_name,
            last_name =  dto.last_name,
            email =  dto.email,
            phone =  dto.phone,
            pswrd =  BCryptPasswordEncoder().encode(dto.password)
        )
    }

    fun toUserResponseDTO(user: User): UserResponseDTO {
        return UserResponseDTO(
            id = user.id!!,
            first_name = user.first_name,
            last_name = user.last_name,
            email = user.email,
            phone = user.phone,
            address = user.address,
             creditCards = user.creditCards,
            products = user.products
        )
    }

}
