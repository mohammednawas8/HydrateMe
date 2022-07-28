package com.example.hydrateme.hydrateme.presentation.util

sealed class Gender(val gender: String) {
    object Male : Gender("Male")
    object Female: Gender("Female")
    object None: Gender("Non")

}
