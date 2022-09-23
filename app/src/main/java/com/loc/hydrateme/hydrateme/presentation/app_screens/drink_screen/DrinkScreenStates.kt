package com.loc.hydrateme.hydrateme.presentation.app_screens.drink_screen

import java.text.SimpleDateFormat
import java.util.*

data class DrinkScreenStates(
    val dailyGoal: Int = 0,
    val complete: Int = 0,
    val waterPercentage: Float = 0.05f,
    val cupSize: Int = 200,
    val date: String = SimpleDateFormat("E, MMMM dd", Locale.ENGLISH).format(Date())
)
