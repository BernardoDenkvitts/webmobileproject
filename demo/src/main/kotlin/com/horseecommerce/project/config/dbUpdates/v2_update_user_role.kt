package com.horseecommerce.project.config.dbUpdates

import com.horseecommerce.project.model.User.UserRole
import com.horseecommerce.project.repository.UserRepository
import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import io.mongock.api.annotations.RollbackExecution

@ChangeUnit(id = "v2_update_user_role", order = "2", author = "bernardo")
class v2_update_user_role(
    private val userRespository: UserRepository
) {
    @Execution
    fun updateUserRole(){
        val users = userRespository.findAll()
        users.forEach{
            user -> val updateRole = user.copy(role = UserRole.USER)
            userRespository.save(updateRole)
        }
    }

    @RollbackExecution
    fun rollbackUpdateRole(){
        userRespository.deleteAll()
    }

}