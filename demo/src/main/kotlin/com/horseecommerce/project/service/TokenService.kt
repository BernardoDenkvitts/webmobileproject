package com.horseecommerce.project.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.horseecommerce.project.model.User.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class TokenService {

    @Value("\${api.security.token.secret}")
    private lateinit var secret: String

    fun generateToken(user: User): String {
        try {
            val algorithm = Algorithm.HMAC256(secret)
            return JWT.create()
                .withIssuer("auth-api")
                .withExpiresAt(tokenExpireHour())
                .withSubject(user.email)
                .sign(algorithm)

        }catch (e: JWTCreationException){
            throw RuntimeException("Error to create the token")
        }
    }


    private fun tokenExpireHour(): Instant {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"))
    }

    fun validateToken(token: String): String {
        try{
            val algorithm = Algorithm.HMAC256(secret)
            return JWT.require(algorithm)
                .withIssuer("auth-api")
                .build()
                .verify(token)
                .subject

        }catch (e: JWTVerificationException){
            throw RuntimeException("Error to validate the token")
        }
    }

}