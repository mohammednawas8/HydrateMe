package com.example.hydrateme.hydrateme.presentation.app_start_screens.util

sealed class AppStartEvents {
    data class GenderChange(val gender: String): AppStartEvents()
    data class WeightChange(val weight: Int): AppStartEvents()
    data class WeightUnit(val unit: String): AppStartEvents()
    data class WakeUpTime(val time: String): AppStartEvents()
    data class BedTime(val time: String): AppStartEvents()
    object Finish: AppStartEvents()
}
