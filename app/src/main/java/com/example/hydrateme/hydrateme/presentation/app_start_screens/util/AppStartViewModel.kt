package com.example.hydrateme.hydrateme.presentation.app_start_screens.util

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hydrateme.hydrateme.data.local.dto.Unit
import com.example.hydrateme.hydrateme.data.local.dto.UserEntity
import com.example.hydrateme.hydrateme.domain.use_case.UseCases
import com.example.hydrateme.hydrateme.presentation.app_start_screens.gender_screen.GenderScreenStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AppStartViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private val _userState = mutableStateOf(AppStartStates())
    val userState: State<AppStartStates> = _userState

    private val _navigate = mutableStateOf(GenderScreenStates(navigate = false, message = ""))
     val navigate: State<GenderScreenStates> = _navigate


    fun onEvent(event: AppStartEvents) {
        when (event) {
            is AppStartEvents.GenderChange -> {
                _userState.value = userState.value.copy(
                    gender = event.gender
                )
            }

            is AppStartEvents.GenderNext -> {
                val result = useCases.validateGender(userState.value.gender)
                if(result.successful)
                    _navigate.value = navigate.value.copy(
                        navigate = true
                    )
                else
                    _navigate.value = navigate.value.copy(
                        navigate = false,
                        message = result.errorMessage.toString()
                    )
            }

            is AppStartEvents.WeightChange -> {
                _userState.value = userState.value.copy(
                    weight = event.weight
                )
            }
            is AppStartEvents.WeightUnit -> {
                _userState.value = userState.value.copy(
                    weightUnit = event.unit
                )
            }
            is AppStartEvents.WakeUpTime -> {
                _userState.value = userState.value.copy(
                    wakeUpTime = event.time
                )
            }
            is AppStartEvents.BedTime -> {
                _userState.value = userState.value.copy(
                    bedTime = event.time
                )
            }
            is AppStartEvents.Finish -> viewModelScope.launch {
                withContext(Dispatchers.Default) { saveUserInformation() }
            }
        }
    }

    private fun saveUserInformation() = viewModelScope.launch {
        val data = _userState.value
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