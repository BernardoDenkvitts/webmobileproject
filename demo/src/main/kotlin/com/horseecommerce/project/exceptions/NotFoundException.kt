package com.horseecommerce.project.exceptions
import java.lang.RuntimeException

class NotFoundException(override val message: String): RuntimeException() {
}