package com.hydrate_me.hydrateme.hydrateme.presentation.app_screens.settings_screen

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hydrate_me.hydrateme.hydrateme.data.mapper.toUserEntity
import com.hydrate_me.hydrateme.hydrateme.domain.alarm_manger.ReminderManger
import com.hydrate_me.hydrateme.hydrateme.domain.model.User
import com.hydrate_me.hydrateme.hydrateme.domain.use_case.UseCases
import com.hydrate_me.hydrateme.hydrateme.presentation.app_screens.settings_screen.components.SettingsScreenEvents
import com.hydrate_me.hydrateme.hydrateme.presentation.app_start_screens.splash_screen.SplashViewModel
import com.hydrate_me.hydrateme.hydrateme.presentation.util.calculateDailyGoal
import com.hydrate_me.hydrateme.hydrateme.presentation.util.hhMM24toAmPm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val useCases: UseCases,
    private val reminderManger: ReminderManger,
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {

    private val _state = mutableStateOf(SettingsState())
    val state: State<SettingsState> = _state

    private val _resetData = MutableSharedFlow<String>()
    val resetData = _resetData.asSharedFlow()

    private lateinit var user: User

    init {
        viewModelScope.launch {
            useCases.getUserUseCase.invoke().collect {
                user = it
                _state.value = state.value.copy(
                    gender = it.gender,
                    weight = it.weight,
                    wakeupTime = hhMM24toAmPm(it.wakeUpHour.toInt(), it.wakeUpMinutes.toInt()),
                    bedTime = hhMM24toAmPm(it.bedHour.toInt(), it.bedMinutes.toInt()),
                    wakeupHour = it.wakeUpHour.toInt(),
                    wakeupMinute = it.wakeUpMinutes.toInt(),
                    bedHour = it.bedHour.toInt(),
                    bedMinute = it.bedMinutes.toInt(),
                    unit = it.unit
                )
            }
        }
    }

    fun onEvent(event: SettingsScreenEvents) {
        when (event) {
            is SettingsScreenEvents.UpdateGender -> {
                viewModelScope.launch {
                    useCases.insertUserUseCase.invoke(
                        user.toUserEntity().copy(gender = event.gender.gender)
                    )
                }
            }
            is SettingsScreenEvents.UpdateWeight -> {
                viewModelScope.launch {
                    updateWeightAndDailyGoal(event.weight, event.unit)
                    useCases.resetUserDataUseCase.invoke(user)
                }
            }
            is SettingsScreenEvents.UpdateWakeupTime -> {
                viewModelScope.launch {
                    updateWakeupAndDailyGoal(event.wakeupHour, event.wakeupMinutes)
                    useCases.resetUserDataUseCase.invoke(user.copy(wakeUpHour = event.wakeupHour,
                        wakeUpMinutes = event.wakeupMinutes))
                }
            }
            is SettingsScreenEvents.UpdateBedTime -> {
                viewModelScope.launch {
                    updateBedTimeAndDailyGoal(event.bedHour, event.bedMinutes)
                    useCases.resetUserDataUseCase(user.copy(bedHour = event.bedHour,
                        bedMinutes = event.bedMinutes))
                }
            }
            is SettingsScreenEvents.ResetData -> {
                sharedPreferences.edit().putBoolean(SplashViewModel.IS_DATABASE_EMPTY, true)
                    .commit()
                viewModelScope.launch {
                    val cancelReminderDeferred = async { reminderManger.cancelAllReminders() }
                    cancelReminderDeferred.await()
                    useCases.clearAlarmsTableUseCase.invoke()
                    useCases.clearDaysTable.invoke()
                    useCases.clearHistoryTable.invoke()
                    _resetData.emit("Data was reset")
                }
            }
        }
    }

    private suspend fun updateBedTimeAndDailyGoal(bedHour: String, bedMinutes: String) {
        Log.d("test22", "$bedHour:$bedMinutes")
        useCases.insertUserUseCase.invoke(
            user.toUserEntity()
                .copy(bedHour = bedHour, bedMinutes = bedMinutes)
        )
    }

    private suspend fun updateWakeupAndDailyGoal(wakeupHour: String, wakeupMinutes: String) {
        useCases.insertUserUseCase.invoke(
            user.toUserEntity().copy(
                wakeUpHour = wakeupHour,
                wakeUpMinutes = wakeupMinutes
            )
        )
    }


    private suspend fun updateWeightAndDailyGoal(weight: Int, unit: String) {
        useCases.insertUserUseCase.invoke(
            user.toUserEntity().copy(weight = weight,
                unit = user.unit.copy(weightUnit = unit),
                dailyGoal = calculateDailyGoal(weight, unit)
            )
        )
    }
}