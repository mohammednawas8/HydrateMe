package com.hydrate_me.hydrateme.hydrateme.presentation.app_screens.reminder_sound_screen

import com.hydrate_me.hydrateme.R

data class ReminderSoundScreenStates(
    val sound: Int = 0,
    val soundsList: List<NotificationSound> = emptyList(),
)

data class NotificationSound(
    val id: Int,
    val name: String
)

val listOfNotificationsId = listOf(
    R.raw.water_drop_deffault,
    R.raw.water_sound_1,
    R.raw.water_sound_2,
    R.raw.notification_sound_1,
    R.raw.notification_sound_2,
)