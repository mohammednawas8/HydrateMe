package com.example.hydrateme.hydrateme.presentation.app_start_screens.util

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hydrateme.hydrateme.data.local.dto.DayEntity
import com.example.hydrateme.hydrateme.data.local.dto.Unit
import com.example.hydrateme.hydrateme.data.local.dto.UserEntity
import com.example.hydrateme.hydrateme.domain.use_case.UseCases
import com.example.hydrateme.hydrateme.presentation.util.yyyyMMddToMillis
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

private val TAG = "AppStartViewModel"
@HiltViewModel
class AppStartViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private val _userState = mutableStateOf(AppStartStates())
    val userState: State<AppStartStates> = _userState


    fun onEvent(event: AppStartEvents) {
        when (event) {
            is AppStartEvents.GenderChange -> {
                _userState.value = userState.value.copy(
                    gender = event.gender
                )
                Log.d(TAG,"Gender Change ${event.gender}")
            }

            is AppStartEvents.WeightChange -> {
                _userState.value = userState.value.copy(
                    weight = event.weight
                )
                Log.d(TAG,"Weight Change ${event.weight}")
            }
            is AppStartEvents.WeightUnitChange -> {
                _userState.value = userState.value.copy(
                    weightUnit = event.unit
                )
                Log.d(TAG,"Weight Unit ${event.unit}")
            }
            is AppStartEvents.WakeUpHourChange -> {
                _userState.value = userState.value.copy(
                    wakeUpHour = event.hour
                )
                Log.d(TAG,"Wake up hour change ${event.hour}")
            }
            is AppStartEvents.WakeUpMinutesChange -> {
                _userState.value = userState.value.copy(
                    wakeUpMinutes = event.minutes
                )
                Log.d(TAG,"Wake up minutes change ${event.minutes}")
            }
            is AppStartEvents.BedHourChange -> {
                _userState.value = userState.value.copy(
                    bedHour = event.hour
                )
                Log.d(TAG,"Bed hour change ${event.hour}")
            }
            is AppStartEvents.BedMinutesChange -> {
                _userState.value = userState.value.copy(
                    bedMinutes = event.minutes
                )
                Log.d(TAG,"Bed minutes change ${event.minutes}")
            }
            is AppStartEvents.Finish -> viewModelScope.launch {
                withContext(Dispatchers.Default) { saveUserInformation() }
                Log.d(TAG,"User saved")
            }
        }
    }

    private fun saveUserInformation() = viewModelScope.launch {
        val data = _userState.value
        val dailyGoal = getDailyGoal(data)

        val user = UserEntity(
            data.gender,
            data.weight,
            data.wakeUpHour,
            data.wakeUpMinutes,
            data.bedHour,
            data.bedMinutes,
            dailyGoal,
            0,
            Unit(
                data.weightUnit,
                "ml"
            ),
            "",
            1
        )

        useCases.insertUseUseCase(user)
        useCases.clearDaysTable()
        useCases.clearHistoryTable()
        addNewDay()
    }

    private suspend fun addNewDay() {
        val insertedDay = useCases.insertDayUseCase()
        Log.d(TAG,"Inserted day $insertedDay")
    }

    private fun getDailyGoal(data: AppStartStates): Int {
        return data.weight * 30
    }

}