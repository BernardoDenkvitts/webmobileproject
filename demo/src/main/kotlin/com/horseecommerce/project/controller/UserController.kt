package com.horseecommerce.project.controller


import com.horseecommerce.project.dtos.User.UserDTO
import com.horseecommerce.project.dtos.User.UserRequestDTO
import com.horseecommerce.project.dtos.User.UserResponseDTO
import com.horseecommerce.project.model.Product.Product
import com.horseecommerce.project.service.User.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI


@RestController
@RequestMapping("/api/usuarios")
class UserController(private val service: UserService) {


    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserResponseDTO>> {
        return ResponseEntity.ok(service.getAllUsers())
    }

    @GetMapping("/{id}")
    fun getUserByID(@PathVariable id: String): ResponseEntity<UserResponseDTO> {
        return ResponseEntity.ok(service.findUserByID(id))
    }

    /*@PostMapping
    @Transactional
    fun createUser(
        @RequestBody @Valid user: UserDTO, uriBuilder: UriComponentsBuilder
    ): ResponseEntity<UserResponseDTO> {
        val newUser: UserResponseDTO = service.createUser(user)
        val uri: URI = uriBuilder.path("/api/usuarios/${newUser.id}").build().toUri()
        return ResponseEntity.created(uri).body(newUser)
    }*/

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deleteUser(@PathVariable id: String){
        service.delete(id)
    }

    @PutMapping("/{id}")
    @Transactional
    fun updateUser(
        @PathVariable id: String, @RequestBody @Valid dto: UserRequestDTO
    ): ResponseEntity<UserResponseDTO>{
        return ResponseEntity.ok(service.updateUser(id, dto))
    }

    @PutMapping("/product/{id}")
    @Transactional
    fun addToProductList(
        @PathVariable id: String, @RequestBody dto: Product
    ): ResponseEntity<UserResponseDTO> {
        return ResponseEntity.ok(service.addToProductList(id, dto))
    }

    @PutMapping("/{id}/product/{productID}")
    @Transactional
    fun removeFromProductList(
        @PathVariable id: String, @PathVariable productID: String
    ): ResponseEntity<UserResponseDTO> {
        return ResponseEntity.ok(service.removeFromProductList(id, productID))
    }

    @PutMapping("/{id}/creditCard")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun removeCreditCard(
        @PathVariable id: String, @RequestBody creditCardNumber: String
    ) {
        return service.removeCreditCard(id, creditCardNumber)
    }


}