package com.example.hydrateme.hydrateme.presentation.app_screens.drink_screen

import java.text.SimpleDateFormat
import java.util.*

data class DrinkScreenStates(
    val dailyGoal: Int = 0,
    val complete: Int = 0,
    val waterPercentage: Float = 0.0f,
    val date: String = SimpleDateFormat("E, MMMM dd", Locale.ENGLISH).format(Date())
)
