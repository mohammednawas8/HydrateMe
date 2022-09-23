package com.loc.hydrateme.hydrateme.presentation.app_screens.settings_screen

import com.loc.hydrateme.hydrateme.data.local.dto.Unit


data class SettingsState(
    val gender: String = "",
    val weight: Int = 0,
    val wakeupTime: String = "6:00",
    val bedTime: String = "22:30",
    val wakeupHour: Int = 6,
    val wakeupMinute: Int = 0,
    val bedHour: Int = 22,
    val bedMinute: Int = 30,
    val unit: Unit = Unit("kg","ml"),
) {
}