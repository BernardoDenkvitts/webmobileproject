package com.horseecommerce.project.exceptions
import java.lang.RuntimeException

class BadRequestException(override val message: String): RuntimeException() {
}