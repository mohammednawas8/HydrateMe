package com.example.hydrateme.hydrateme.presentation.app_start_screens.util

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hydrateme.hydrateme.data.local.dto.Unit
import com.example.hydrateme.hydrateme.data.local.dto.UserEntity
import com.example.hydrateme.hydrateme.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AppStartViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private val _state = mutableStateOf(AppStartStates())
    private val state: State<AppStartStates> = _state

    private var _weightFlow = MutableSharedFlow<String>()
    var weightFlow: SharedFlow<String> = _weightFlow

    fun onEvent(event: AppStartEvents) {
        when (event) {
            is AppStartEvents.GenderChange -> {
                if (event.gender.isBlank()) {
                    viewModelScope.launch {
                        _weightFlow.emit("Please select your gender")
                    }
                } else
                    _state.value = state.value.copy(
                        gender = event.gender
                    )
            }
            is AppStartEvents.WeightChange -> {
                _state.value = state.value.copy(
                    weight = event.weight
                )
            }
            is AppStartEvents.WeightUnit -> {
                _state.value = state.value.copy(
                    weightUnit = event.unit
                )
            }
            is AppStartEvents.WakeUpTime -> {
                _state.value = state.value.copy(
                    wakeUpTime = event.time
                )
            }
            is AppStartEvents.BedTime -> {
                _state.value = state.value.copy(
                    bedTime = event.time
                )
            }
            is AppStartEvents.Finish -> viewModelScope.launch {
                withContext(Dispatchers.Default) { saveUserInformation() }
            }
        }
    }

    private fun saveUserInformation() = viewModelScope.launch {
        val data = _state.value
        val dailyGoal = getDailyGoal(data)

        val user = UserEntity(
            data.gender,
            data.weight,
            data.wakeUpTime,
            data.bedTime,
            dailyGoal,
            0,
            Unit(data.weightUnit, "ml"),
            ""
        )

        useCases.insertUserUseCase(user)
    }

    private fun getDailyGoal(data: AppStartStates): Int {
        return data.weight * 30
    }

}