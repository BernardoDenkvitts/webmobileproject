package com.horseecommerce.project.controller

import com.horseecommerce.project.dtos.Authentication.LoginDTO
import com.horseecommerce.project.dtos.User.UserDTO
import com.horseecommerce.project.dtos.User.UserResponseDTO
import com.horseecommerce.project.service.User.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder


@RestController
@RequestMapping("auth")
class AuthenticationController(
    val authenticationManager: AuthenticationManager,
    val userService: UserService
) {

    @PostMapping("/login")
    fun login(@RequestBody dto: LoginDTO): ResponseEntity<String>{
        val userPassword = UsernamePasswordAuthenticationToken(dto.email, dto.password)
        authenticationManager.authenticate(userPassword)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/register")
    fun register(@RequestBody dto: UserDTO, uriBuilder: UriComponentsBuilder): ResponseEntity<UserResponseDTO> {
        val newUser = userService.createUser(dto)
        val uri = uriBuilder.path("/api/usuarios/${newUser.id}").build().toUri()
        return ResponseEntity.created(uri).body(newUser)
    }

}