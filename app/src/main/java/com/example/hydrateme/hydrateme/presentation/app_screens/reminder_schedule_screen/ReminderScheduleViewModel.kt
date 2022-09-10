package com.example.hydrateme.hydrateme.presentation.app_screens.reminder_schedule_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hydrateme.hydrateme.domain.model.Alarm
import com.example.hydrateme.hydrateme.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderScheduleViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    private val _state = mutableStateOf<List<Alarm>>(emptyList())
    val state: State<List<Alarm>> = _state

    init {
        viewModelScope.launch {
            useCases.getAlarms.invoke().collectLatest {
                _state.value = it.map { it }
            }
        }
    }

    fun onEvent(event: ReminderScheduleScreenEvents){
        when(event){
            is ReminderScheduleScreenEvents.SwitchChange ->{
                if(event.isChecked){
                    setAlarm(event.alarm)
                }else{
                   cancelAlarm(event.alarm)
                }
            }
            is ReminderScheduleScreenEvents.SetAlarm ->{

            }
        }
    }

    private fun cancelAlarm(alarm: Alarm) {

    }

    private fun setAlarm(alarm: Alarm) {

    }
}