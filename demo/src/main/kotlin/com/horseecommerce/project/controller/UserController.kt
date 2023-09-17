package com.horseecommerce.project.controller

import com.horseecommerce.project.dtos.User.UserResponseDTO
import com.horseecommerce.project.service.User.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/usuarios")
class UserController(private val service: UserService) {

    @GetMapping("/{id}")
    fun getUserByID(@PathVariable id: String): ResponseEntity<UserResponseDTO> {
        return ResponseEntity.ok(service.findUserByID(id))
    }

}