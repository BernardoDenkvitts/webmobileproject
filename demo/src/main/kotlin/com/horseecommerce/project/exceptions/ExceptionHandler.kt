package com.horseecommerce.project.exceptions

import com.horseecommerce.project.dtos.ErrorResponseDTO
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(ThrowException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handlerNotFound(
        exception: ThrowException,
        request: HttpServletRequest
    ): ErrorResponseDTO {
        return ErrorResponseDTO(
            status = HttpStatus.NOT_FOUND.value(), error = HttpStatus.NOT_FOUND.name,
            message = exception.message, path = request.servletPath
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handlerServerError(
        exception: Exception,
        request: HttpServletRequest
    ): ErrorResponseDTO {
        return ErrorResponseDTO(status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                error = HttpStatus.INTERNAL_SERVER_ERROR.name,
                                message = exception.message ?: "Unknown Error",
                                path = request.servletPath
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationError(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ErrorResponseDTO {
        val errorMessage = HashMap<String, String>()
        exception.bindingResult.fieldErrors.forEach{
            errorMessage[it.field] = it.defaultMessage ?: "Unknown Error"
        }

        return ErrorResponseDTO(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = errorMessage.toString(),
            path = request.servletPath
        )
    }

}