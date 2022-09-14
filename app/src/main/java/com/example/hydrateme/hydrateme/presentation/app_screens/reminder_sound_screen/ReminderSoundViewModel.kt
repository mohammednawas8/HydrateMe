package com.example.hydrateme.hydrateme.presentation.app_screens.reminder_sound_screen

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.res.Resources
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hydrateme.HydrateApplication
import com.example.hydrateme.R
import com.example.hydrateme.hydrateme.data.mapper.toUserEntity
import com.example.hydrateme.hydrateme.domain.model.User
import com.example.hydrateme.hydrateme.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderSoundViewModel @Inject constructor(
    private val useCases: UseCases,
    private val resources: Resources,
    private val packageName: String,
    private val notificationManager:NotificationManager
) : ViewModel() {

    private val _state = mutableStateOf(ReminderSoundScreenStates())
    val state: State<ReminderSoundScreenStates> = _state

    private lateinit var user: User

    init {
        viewModelScope.launch {
            useCases.getUserUseCase.invoke().collectLatest {
                user = it
                _state.value = state.value.copy(sound = it.soundPath?: R.raw.water_drop_deffault)
            }
        }

        viewModelScope.launch {
            val listNotificationSound: MutableList<NotificationSound> = mutableListOf()
            listOfNotificationsId.forEach {
                listNotificationSound.add(NotificationSound(it, resources.getResourceEntryName(it)))
            }
            _state.value = state.value.copy(soundsList = listNotificationSound)
        }
    }

    fun onEvent(event: ReminderSoundScreenEvents) {
        when (event) {
            is ReminderSoundScreenEvents.ChangeSound -> {
                viewModelScope.launch {
                    useCases.insertUserUseCase.invoke(user.copy(soundPath = event.soundId).toUserEntity()) //In here i should pass the user not userEntity
                }
            }
        }
    }

}