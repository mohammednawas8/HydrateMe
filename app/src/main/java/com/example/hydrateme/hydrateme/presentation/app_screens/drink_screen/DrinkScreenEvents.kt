package com.example.hydrateme.hydrateme.presentation.app_screens.drink_screen

sealed class DrinkScreenEvents {
    object Drink: DrinkScreenEvents()
    data class ChangeCupSize(val cupItem: CupItem): DrinkScreenEvents()
}