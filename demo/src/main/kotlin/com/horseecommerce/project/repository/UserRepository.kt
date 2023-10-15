package com.horseecommerce.project.repository

import com.horseecommerce.project.model.User.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: MongoRepository<User, String> {

    fun findByEmail(email: String): UserDetails?

}