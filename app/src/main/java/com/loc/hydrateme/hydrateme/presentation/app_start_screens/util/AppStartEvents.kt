package com.loc.hydrateme.hydrateme.presentation.app_start_screens.util

sealed class AppStartEvents {
    data class GenderChange(val gender: String): AppStartEvents()
    data class WeightChange(val weight: Int): AppStartEvents()
    data class WeightUnitChange(val unit: String): AppStartEvents()
    data class WakeUpHourChange(val hour: String): AppStartEvents()
    data class WakeUpMinutesChange(val minutes: String): AppStartEvents()
    data class BedHourChange(val hour: String): AppStartEvents()
    data class BedMinutesChange(val minutes: String): AppStartEvents()
    object Finish: AppStartEvents()
}
