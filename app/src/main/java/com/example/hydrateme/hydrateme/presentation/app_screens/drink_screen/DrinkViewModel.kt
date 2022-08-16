package com.example.hydrateme.hydrateme.presentation.app_screens.drink_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hydrateme.hydrateme.data.local.dto.DayEntity
import com.example.hydrateme.hydrateme.data.local.dto.HistoryEntity
import com.example.hydrateme.hydrateme.domain.use_case.UseCases
import com.example.hydrateme.hydrateme.presentation.util.yyyyMMddToMillis
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DrinkViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private var _state = mutableStateOf(DrinkScreenStates())
    var state: State<DrinkScreenStates> = _state

    //Note : If the day not found (null) that means it's a new day so we need to insert the day
    private var day: Long? = null


    init {
        viewModelScope.launch {
            //Insert new day each time we open the drink screen - if the day is already in the
            //Table, ignore the insertion.
            val day = async { useCases.insertDayUseCase() }
            day.await()
            getUser()
            //Bring the last day was added in the Day table to be able to insert new history record under that day
            // المشكلة انو لما اجيب اخر يوم خصوصا بعد حفظ معلومات جديد
            // حليتها عن طريق اني اخلي اللي راجع nullable ورجعتو بفلو
            getLastDay()
        }
    }


    private fun getLastDay() {
        viewModelScope.launch {
            useCases.getLastDayUseCase.invoke().collect{
                it?.let {
                    day = it.day
                }
            }
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            useCases.getUserUseCase
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
                    launch { drink(event.drinkAmount) }
                    launch { insertHistoryRecord(event.drinkAmount) }
                }
            }
        }
    }

    private suspend fun insertHistoryRecord(drinkAmount: Int) {
        if (day != null) {
            useCases.insertHistoryRecord(
                HistoryEntity(
                    Date().time,
                    drinkAmount,
                    day!!
                )
            )
            //If the days table is empty for some reason insert new day and get the last day then call the function itself
        } else {
            useCases.insertDayUseCase()
            getLastDay()
            insertHistoryRecord(drinkAmount)
        }

    }

    private suspend fun drink(drinkAmount: Int) {
        useCases.drinkUseCase(drinkAmount + state.value.complete)
    }
}