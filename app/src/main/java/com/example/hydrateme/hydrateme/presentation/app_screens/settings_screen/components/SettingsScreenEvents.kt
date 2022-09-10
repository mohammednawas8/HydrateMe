package com.example.hydrateme.hydrateme.presentation.app_screens.settings_screen.components

import com.example.hydrateme.hydrateme.presentation.util.Gender

sealed class SettingsScreenEvents {
    data class UpdateGender(val gender: Gender): SettingsScreenEvents()
    data class UpdateWeight(val weight: Int, val unit: String): SettingsScreenEvents()
    data class UpdateWakeupTime(val wakeupHour: String, val wakeupMinutes: String): SettingsScreenEvents()
    data class UpdateBedTime(val bedHour: String, val bedMinutes: String): SettingsScreenEvents()
}