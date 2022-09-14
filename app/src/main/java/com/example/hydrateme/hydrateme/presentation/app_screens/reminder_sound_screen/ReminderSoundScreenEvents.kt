package com.example.hydrateme.hydrateme.presentation.app_screens.reminder_sound_screen

sealed class ReminderSoundScreenEvents {
    data class ChangeSound(val soundId: Int): ReminderSoundScreenEvents()
}