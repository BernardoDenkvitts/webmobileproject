package com.horseecommerce.project.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfigurations {

    //TODO fazer para o endpoint de PUT no user caso queira alterar algo
    // fazer para o endpoint de PUT no products caso queira alterar algo
    // ver um jeito de so permitir alterar se os ids baterem, e do produto se o id do criador do produto bate com o id
    // que ta tentando acessar o endpoint

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        val endpointProducts: String = "/api/products"
        val endpointUsers: String = "/api/usuarios"

        return httpSecurity.csrf{it.disable()}
            .sessionManagement{
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests {
                it.requestMatchers(HttpMethod.POST, endpointProducts).hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.GET, endpointProducts, "$endpointProducts/*").permitAll()
                    .requestMatchers(HttpMethod.GET, endpointUsers).hasRole("ADMIN")
                    .requestMatchers(endpointUsers).hasRole("ADMIN")
                    .requestMatchers("/auth/*").permitAll()
                    .anyRequest().authenticated()
            }
            .build()
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun enconderPassword(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}