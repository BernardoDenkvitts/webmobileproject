package com.horseecommerce.project.model.User

import com.horseecommerce.project.model.Product.Product
import jakarta.validation.Valid
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Document("user")
data class User(

    @field:Id
    val id: String? = null,
    val first_name: String,
    val last_name: String,

    @field:Indexed(name = "UserEmailIndex", unique = true)
    val email: String,
    val phone: String,

    @Field("password")
    val pswrd: String,

    @field:Enumerated(value = EnumType.STRING)
    val role: UserRole = UserRole.USER,

    @field:Valid
    val address: Address? = null,   // Assim que o usuario cria a conta ele não precisa dizer o endereço

    val creditCards: MutableSet<UserCreditCard> = mutableSetOf(),

    val products: MutableList<Product> = mutableListOf()

): UserDetails {
    override fun getAuthorities(): MutableList<SimpleGrantedAuthority> {
        if(role == UserRole.ADMIN){
            return mutableListOf(
                SimpleGrantedAuthority("ROLE_ADMIN"),
                SimpleGrantedAuthority("ROLE_USER")
            )
        }
        return mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
    }

    override fun getPassword(): String = pswrd

    override fun getUsername(): String = email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

}

