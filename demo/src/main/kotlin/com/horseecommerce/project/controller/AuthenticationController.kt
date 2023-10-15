package com.horseecommerce.project.controller

import com.horseecommerce.project.dtos.Authentication.LoginDTO
import com.horseecommerce.project.dtos.User.UserDTO
import com.horseecommerce.project.dtos.User.UserResponseDTO
import com.horseecommerce.project.model.User.User
import com.horseecommerce.project.service.TokenService
import com.horseecommerce.project.service.User.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import com.horseecommerce.project.dtos.Authentication.LoginResponseDTO
import jakarta.validation.Valid
import org.springframework.transaction.annotation.Transactional


@RestController
@RequestMapping("auth")
class AuthenticationController(
    val authenticationManager: AuthenticationManager,
    val userService: UserService,
    val tokenService: TokenService
) {

    @PostMapping("/login")
    fun login(@RequestBody @Valid dto: LoginDTO): ResponseEntity<LoginResponseDTO>{
        val userPassword = UsernamePasswordAuthenticationToken(dto.email, dto.password)
        val auth = authenticationManager.authenticate(userPassword)
        val token = tokenService.generateToken(auth.principal as User)
        return ResponseEntity.ok(LoginResponseDTO(token))
    }

    @PostMapping("/register")
    @Transactional
    fun register(@RequestBody @Valid dto: UserDTO, uriBuilder: UriComponentsBuilder): ResponseEntity<UserResponseDTO> {
        val newUser = userService.createUser(dto)
        val uri = uriBuilder.path("/api/usuarios/${newUser.id}").build().toUri()
        return ResponseEntity.created(uri).body(newUser)
    }

}