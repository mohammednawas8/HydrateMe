package com.example.hydrateme.hydrateme.presentation.app_screens.drink_screen

sealed class DrinkScreenEvents {
    data class Drink(val drinkAmount: Int): DrinkScreenEvents()
}