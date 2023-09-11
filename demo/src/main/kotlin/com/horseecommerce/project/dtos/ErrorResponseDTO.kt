package com.horseecommerce.project.dtos

import java.security.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime

data class ErrorResponseDTO(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val message: String,
    val error: String,
    val path: String
)
