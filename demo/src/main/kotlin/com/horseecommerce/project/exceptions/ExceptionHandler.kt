package com.horseecommerce.project.exceptions

import com.horseecommerce.project.dtos.ErrorResponseDTO
import jakarta.servlet.http.HttpServletRequest
import org.springframework.data.mongodb.core.aggregation.BooleanOperators.Not
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpServerErrorException.InternalServerError
import java.lang.Exception

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handlerNotFound(
        exception: NotFoundException,
        request: HttpServletRequest
    ): ErrorResponseDTO {
        return ErrorResponseDTO(status =  HttpStatus.NOT_FOUND.value(), error = HttpStatus.NOT_FOUND.name,
                                message = exception.message, path = request.servletPath
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handlerServerError(
        exception: InternalServerErrorException,
        request: HttpServletRequest
    ): ErrorResponseDTO {
        return ErrorResponseDTO(status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                error = HttpStatus.INTERNAL_SERVER_ERROR.name,
                                message = exception.message,
                                path = request.servletPath
        )
    }

    /*@ExceptionHandler(BadRequestException::class)
    fun handlerBadRequest(
        exception: BadRequestException,
        request: HttpServletRequest
    ): ErrorResponseDTO {
        return  ErrorResponseDTO(status = HttpStatus.BAD_REQUEST.value(),
                                 error = HttpStatus.BAD_REQUEST.name,
                                 message = exception.message,
                                 path = request.servletPath
        )
    }   */


}