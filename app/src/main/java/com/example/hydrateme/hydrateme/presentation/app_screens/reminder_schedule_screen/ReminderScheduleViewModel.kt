package com.example.hydrateme.hydrateme.presentation.app_screens.reminder_schedule_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hydrateme.hydrateme.domain.alarm_manger.ReminderManger
import com.example.hydrateme.hydrateme.domain.model.Alarm
import com.example.hydrateme.hydrateme.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderScheduleViewModel @Inject constructor(
    private val useCases: UseCases,
    private val reminderManger: ReminderManger,
) : ViewModel() {
    private val _state = mutableStateOf<List<Alarm>>(emptyList())
    val state: State<List<Alarm>> = _state

    init {
        viewModelScope.launch {
            useCases.getAlarms.invoke().collectLatest {
                _state.value = it.map { it }.sortedBy { it.hour }
            }
        }
    }

    fun onEvent(event: ReminderScheduleScreenEvents) {
        when (event) {
            is ReminderScheduleScreenEvents.SwitchChange -> {
                if (event.isChecked) {
                    viewModelScope.launch {
                        setAlarm(event.alarm)
                    }
                } else {
                    viewModelScope.launch {
                        cancelAlarm(event.alarm)
                    }
                }
            }
            is ReminderScheduleScreenEvents.SetAlarm -> {
                viewModelScope.launch {
                    setAlarm(Alarm(state.value.maxOf { it.id + 1 },
                        event.hour,
                        event.min,
                        emptyList(),
                        true))
                }
            }
        }
    }

    private suspend fun cancelAlarm(alarm: Alarm) {
        reminderManger.cancelDrinkReminder(alarm)
        useCases.insertAlarmUseCase.invoke(alarm.copy(isEnabled = false))
    }

    private suspend fun setAlarm(alarm: Alarm) {
        useCases.insertAlarmUseCase.invoke(alarm.copy(isEnabled = true))
        reminderManger.setDrinkReminders()
    }
}