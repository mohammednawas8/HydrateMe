package com.example.hydrateme.hydrateme.presentation.util

sealed class Gender(val gender: String) {
    class Male : Gender("Male")
    class Female() : Gender("Female")
}
