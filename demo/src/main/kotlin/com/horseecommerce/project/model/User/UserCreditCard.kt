package com.horseecommerce.project.model.User

import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class UserCreditCard(
    @field:Pattern(regexp = "\\d{4}-\\d{4}-\\d{4}-\\d{4}", message = "Invalid Credit Card number")
    val creditCardNumber: String,
    @field:Size(min = 2, max = 30, message = "Name length must be between 2-30")
    val creditCardName: String,
    @field:Pattern(regexp = "\\d{2}\\/\\d{2}", message = "Validation Date must be moth/year")
    val validationDate: String
)

