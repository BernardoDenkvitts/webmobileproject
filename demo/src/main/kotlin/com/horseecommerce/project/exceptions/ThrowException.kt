package com.horseecommerce.project.exceptions
import java.lang.RuntimeException

class ThrowException(override val message: String): RuntimeException() {
}