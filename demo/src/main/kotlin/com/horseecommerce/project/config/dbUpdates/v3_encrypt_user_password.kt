package com.horseecommerce.project.config.dbUpdates

import com.horseecommerce.project.repository.UserRepository
import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import io.mongock.api.annotations.RollbackExecution
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@ChangeUnit(id = "v3_encrypt_user_passwords", order = "3", author = "bernardo")
class v3_encrypt_user_password(private val userRepository: UserRepository) {

    @Execution
    fun encryptUserPasswords(){
        val users = userRepository.findAll()
        users.forEach { user -> val encryptedPswd = user.copy(pswrd = BCryptPasswordEncoder().encode(user.password))
                                userRepository.save(encryptedPswd)
        }

    }

    @RollbackExecution
    fun rollBackEncryptPassword(){
        userRepository.deleteAll()
    }

}