package com.horseecommerce.project.migration

import com.horseecommerce.project.model.Product.Product
import com.horseecommerce.project.model.Product.TypeProduct
import com.horseecommerce.project.model.User.Address
import com.horseecommerce.project.model.User.User
import com.horseecommerce.project.repository.ProductRepository
import com.horseecommerce.project.repository.UserRepository
import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import io.mongock.api.annotations.RollbackExecution

@ChangeUnit(id = "v1_add_data", order = "1", author = "bernardo")
class v1_add_data(private val productRespository: ProductRepository,
                  private val userRespository: UserRepository
){

    @Execution
    fun addNewData(){
        val newProducts: MutableList<Product> = mutableListOf()
        newProducts.add(Product(name = "Rogerio", price = 4000, quantity = 2, type = TypeProduct.Fast))
        newProducts.add(Product(name = "Pedro", price = 2500, quantity = 1, type = TypeProduct.Casual))
        newProducts.add(Product(name = "Arthur", price = 9000, quantity = 1, type = TypeProduct.Rare))
        newProducts.add(Product(name = "João", price = 3000, quantity = 1, type = TypeProduct.Fast))
        newProducts.add(Product(name = "Lucas", price = 5500, quantity = 3, type = TypeProduct.Casual))
        productRespository.saveAll(newProducts)

        val newUsers: MutableList<User> = mutableListOf()
        newUsers.add(User(first_name = "João", last_name = "Sobrenome1", password = "5555", email = "joao@gmail.com",
            phone = "54653524312", address = Address(city = "Passo-Fundo", street = "Centro", st_num = 123)))

        newUsers.add(User(first_name = "Gabriel", last_name = "Sobrenome2", password = "4444", email = "gabriel@gmail.com",
            phone = "5465456436", address = Address(city = "Tapejara", street = "RualQualquer1", st_num = 321)))

        newUsers.add(User(first_name = "Emerson", last_name = "Sobrenome3", password = "3333", email = "emerson@yahoo.com",
            phone = "54657865765", address = Address(city = "Tapera", street = "RualQualquer2", st_num = 545)))

        newUsers.add(User(first_name = "Isadora", last_name = "Sobrenome4", password = "1111", email = "isadora@gmail.com",
            phone = "5443241223", address = Address(city = "Porto-Alegre", street = "RuaQualquer3", st_num = 222)))

        newUsers.add(User(first_name = "Luis", last_name = "Sobrenome5", password = "77777", email = "luis@gmail.com",
            phone = "5498765234", address = Address(city = "Espumoso", street = "RuaQualquer4", st_num = 45)))
        userRespository.saveAll(newUsers)
    }

    @RollbackExecution
    fun rollbackAddNewData(){
        productRespository.deleteAll()
        userRespository.deleteAll()
    }
}