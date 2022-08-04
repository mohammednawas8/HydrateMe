package com.example.hydrateme.hydrateme.presentation.app_screens.drink_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hydrateme.hydrateme.data.local.dto.HistoryEntity
import com.example.hydrateme.hydrateme.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private var _state = mutableStateOf(DrinkScreenStates())
    var state: State<DrinkScreenStates> = _state

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            useCases.getUseUseCase
                .invoke().collect { user ->
                    _state.value = state.value.copy(
                        dailyGoal = user.dailyGoal,
                        complete = user.complete,
                        waterPercentage = user.complete.toFloat() / user.dailyGoal.toFloat()
                    )
                }
        }
    }

    fun onEvent(event: DrinkScreenEvents) {
        when (event) {
            is DrinkScreenEvents.Drink -> {
                viewModelScope.launch {
                    val drinkAmount = state.value.complete + event.drinkAmount
                    useCases.drinkUseCase(drinkAmount)
                    useCases.insertHistoryUseCase(
                        HistoryEntity(System.currentTimeMillis(), drinkAmount,1)
                    )
                }
            }
        }
    }

}