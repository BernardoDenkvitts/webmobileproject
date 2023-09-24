package com.horseecommerce.project.service.User

import com.horseecommerce.project.converters.UserConverter
import com.horseecommerce.project.dtos.User.UserDTO
import com.horseecommerce.project.dtos.User.UserRequestDTO
import com.horseecommerce.project.dtos.User.UserResponseDTO
import com.horseecommerce.project.exceptions.NotFoundException
import com.horseecommerce.project.model.Product.Product
import com.horseecommerce.project.repository.ProductRepository
import com.horseecommerce.project.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

private const val USER_NOT_FOUND_MESSAGE = "User not found"
private const val ERROR_TO_UPDATE_USER = "Error to update the user"
private const val ERROR_INTERNO = "Internal Error"
private const val PRODUCT_ID_NOT_FOUND = "Product doesn't exist"

@Service
class UserService(
    private val repository: UserRepository,
    private val productRepository: ProductRepository,
    private val converter: UserConverter
): IUserService {

    override fun getAllUsers(): List<UserResponseDTO> {
        return repository.findAll().map(converter::toUserResponseDTO)
    }

    override fun findUserByID(id: String): UserResponseDTO {
        val user = repository.findByIdOrNull(id) ?: throw NotFoundException(USER_NOT_FOUND_MESSAGE)
        return converter.toUserResponseDTO(user)
    }

    override fun createUser(dto: UserDTO): UserResponseDTO {
        val newUser = repository.save(converter.toUser(dto))
        return converter.toUserResponseDTO(newUser)
    }

    override fun updateUser(id: String, dto: UserRequestDTO): UserResponseDTO {
        val user = repository.findByIdOrNull(id) ?: throw NotFoundException(USER_NOT_FOUND_MESSAGE)
        val address = user.address.copy(
            city = if(dto.address.city.isNullOrBlank()) user.address.city else dto.address.city,
            street = if(dto.address.street.isNullOrBlank()) user.address.street else dto.address.street,
            st_num = if(dto.address.st_num == null || (dto.address.st_num < 0
                        || dto.address.st_num > 10000)) user.address.st_num
            else dto.address.st_num
        )

        val update = user.copy(
            first_name = if(dto.first_name.isNullOrBlank()) user.first_name else dto.first_name,
            last_name = if(dto.last_name.isNullOrBlank()) user.last_name else dto.last_name,
            email = if(dto.email.isNullOrBlank()) user.email else dto.email,
            phone =  if(dto.phone.isNullOrBlank()) user.phone else dto.phone,
            password = if(dto.password.isNullOrBlank()) user.password else dto.password,
            address = address,
            products = user.products
        )

        repository.save(update)

        return converter.toUserResponseDTO(update)
    }

    override fun addToProductList(id: String, dto: Product): UserResponseDTO {
        val user = repository.findByIdOrNull(id) ?: throw NotFoundException(USER_NOT_FOUND_MESSAGE)
        productRepository.findByIdOrNull(dto.id) ?: throw NotFoundException(PRODUCT_ID_NOT_FOUND)
        user.products.add(dto)
        repository.save(user)
        return converter.toUserResponseDTO(user)
    }

    override fun removeFromProductList(id: String, productID: String): UserResponseDTO {
        val user = repository.findByIdOrNull(id) ?: throw NotFoundException(USER_NOT_FOUND_MESSAGE)
        productRepository.findByIdOrNull(productID) ?: throw NotFoundException(PRODUCT_ID_NOT_FOUND)
        user.products.removeIf { it.id == productID }
        repository.save(user)
        return converter.toUserResponseDTO(user)
    }


    override fun delete(id: String) {
        repository.deleteById(id)
    }

}