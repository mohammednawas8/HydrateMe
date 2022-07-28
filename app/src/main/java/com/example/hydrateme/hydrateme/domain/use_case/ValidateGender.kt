package com.example.hydrateme.hydrateme.domain.use_case

import com.example.hydrateme.hydrateme.presentation.util.Gender

class ValidateGender {

    operator fun invoke(gender: String): ValidationResult {
        if (gender.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Gender can't be empty"
            )
        }else
            return ValidationResult(
                successful = true,
                errorMessage = null
            )
    }

    data class ValidationResult(
        val successful: Boolean,
        val errorMessage: String? = null
    )
}