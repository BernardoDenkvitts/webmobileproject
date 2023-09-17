package com.horseecommerce.project.repository

import com.horseecommerce.project.model.User.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: MongoRepository<User, String> {

}