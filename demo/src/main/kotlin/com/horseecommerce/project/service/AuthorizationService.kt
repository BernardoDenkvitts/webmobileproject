package com.horseecommerce.project.service

import com.horseecommerce.project.exceptions.NotFoundException
import com.horseecommerce.project.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service
class AuthorizationService(val userRepository: UserRepository): UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        return userRepository.findByEmail(email) ?: throw NotFoundException("USER NOT FOUND")
    }

}