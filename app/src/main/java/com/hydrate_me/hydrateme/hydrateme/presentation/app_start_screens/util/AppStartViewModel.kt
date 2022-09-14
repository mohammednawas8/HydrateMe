package com.hydrate_me.hydrateme.hydrateme.presentation.app_start_screens.util

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hydrate_me.hydrateme.R
import com.hydrate_me.hydrateme.hydrateme.data.local.dto.Unit
import com.hydrate_me.hydrateme.hydrateme.data.local.dto.UserEntity
import com.hydrate_me.hydrateme.hydrateme.domain.alarm_manger.ReminderManger
import com.hydrate_me.hydrateme.hydrateme.domain.use_case.UseCases
import com.hydrate_me.hydrateme.hydrateme.presentation.app_start_screens.splash_screen.SplashViewModel.Companion.IS_DATABASE_EMPTY
import com.hydrate_me.hydrateme.hydrateme.presentation.app_start_screens.wakeup_screen.WakeupScreenState
import com.hydrate_me.hydrateme.hydrateme.presentation.util.calculateDailyGoal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

private val TAG = "AppStartViewModel"

@HiltViewModel
class AppStartViewModel @Inject constructor(
    private val useCases: UseCases,
    private val reminderManger: ReminderManger,
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {

    private val _userState = mutableStateOf(AppStartStates())
    val userState: State<AppStartStates> = _userState

    private val _wakeupScreen = mutableStateOf(WakeupScreenState())
    val wakeupScreen: State<WakeupScreenState> = _wakeupScreen


    fun onEvent(event: AppStartEvents) {
        when (event) {
            is AppStartEvents.GenderChange -> {
                _userState.value = userState.value.copy(
                    gender = event.gender
                )
                Log.d(TAG, "Gender Change ${event.gender}")
            }

            is AppStartEvents.WeightChange -> {
                _userState.value = userState.value.copy(
                    weight = event.weight
                )
                Log.d(TAG, "Weight Change ${event.weight}")
            }
            is AppStartEvents.WeightUnitChange -> {
                _userState.value = userState.value.copy(
                    weightUnit = event.unit
                )
                Log.d(TAG, "Weight Unit ${event.unit}")
            }
            is AppStartEvents.WakeUpHourChange -> {
                _userState.value = userState.value.copy(
                    wakeUpHour = event.hour
                )
                Log.d(TAG, "Wake up hour change ${event.hour}")
            }
            is AppStartEvents.WakeUpMinutesChange -> {
                _userState.value = userState.value.copy(
                    wakeUpMinutes = event.minutes
                )
                Log.d(TAG, "Wake up minutes change ${event.minutes}")
            }
            is AppStartEvents.BedHourChange -> {
                _userState.value = userState.value.copy(
                    bedHour = event.hour
                )
                Log.d(TAG, "Bed hour change ${event.hour}")
            }
            is AppStartEvents.BedMinutesChange -> {
                _userState.value = userState.value.copy(
                    bedMinutes = event.minutes
                )
                Log.d(TAG, "Bed minutes change ${event.minutes}")
            }
            is AppStartEvents.Finish -> viewModelScope.launch {
                withContext(Dispatchers.Default) { finishSettings() }
                Log.d(TAG, "User saved")
            }
        }
    }

    private fun finishSettings() {
        saveUserInformationAndAlarms()
        updateSharedPreferences()
    }

    private fun updateSharedPreferences() {
        sharedPreferences.edit().putBoolean(IS_DATABASE_EMPTY, false).apply()
    }

    private fun saveUserInformationAndAlarms(
    ) = viewModelScope.launch {
        val data = _userState.value
        val dailyGoal = getDailyGoal(data)

        val user = UserEntity(
            data.gender,
            data.weight,
            data.wakeUpHour,
            data.wakeUpMinutes,
            200,
            data.bedHour,
            data.bedMinutes,
            dailyGoal,
            0,
            Unit(
                data.weightUnit,
                "ml"
            ),
            R.raw.water_drop_deffault,
            0
        )

        launch { useCases.insertUserUseCase(user) }
        launch { useCases.clearDaysTable() }
        launch { useCases.clearHistoryTable() }
        launch { addNewDay() }
        launch { reminderManger.setInsertDayAlarm() }
        val alarmsDeferred = async {
            saveReminderAlarms(
                "${data.wakeUpHour}:${data.wakeUpMinutes}",
                "${data.bedHour}:${data.bedMinutes}",
                200,
                dailyGoal
            )
        }
        alarmsDeferred.await()
        reminderManger.setDrinkReminders()
    }

    private suspend fun saveReminderAlarms(
        wakeUpTime: String,
        sleepTime: String,
        drinkAmount: Int,
        target: Int,
    ) {
        useCases.saveReminderAlarms.invoke(wakeUpTime, sleepTime, drinkAmount, target)
    }

    private suspend fun addNewDay() {
        val insertedDay = useCases.insertDayUseCase()
        Log.d(TAG, "Inserted day $insertedDay")
    }

    private fun getDailyGoal(data: AppStartStates): Int {
        return calculateDailyGoal(data.weight, data.weightUnit)
    }


}