package com.horseecommerce.project.config

import com.horseecommerce.project.repository.UserRepository
import com.horseecommerce.project.service.TokenService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.catalina.User
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter


@Component
class SecurityFilter(
    val tokenService: TokenService,
    val userRepository: UserRepository
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ){
        val token = recoverToken(request)
        if(token != null){
            val email = tokenService.validateToken(token)
            val user = userRepository.findByEmail(email)
            val authentication = UsernamePasswordAuthenticationToken(user, null, user!!.authorities)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    private fun recoverToken(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization")
        return authHeader?.replace("Bearer ", "")
    }

}