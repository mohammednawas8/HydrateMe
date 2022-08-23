package com.example.hydrateme.hydrateme.presentation.app_screens.statistcs_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hydrateme.hydrateme.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private var _state = mutableStateOf(StatisticsScreenStates())
    val state: State<StatisticsScreenStates> = _state


    init {
        viewModelScope.launch {
            useCases.getTodayReportUseCase(1).collect {
                _state.value = state.value.copy(
                    dailyReport = it.asReversed().map {
                        val hour = String.format("%02d",
                            SimpleDateFormat("k").format(Date(it.time)).toInt())
                        val minuit = String.format("%02d",
                            SimpleDateFormat("m").format(Date(it.time)).toInt())
                        TodayItem(
                            it.drinkAmount,
                            "ml",
                            "${hour}:$minuit"
                        )
                    }
                )
            }
        }
        get10DaysReport()
    }


    fun onEvent(event: StatisticsScreenEvents) {
        when (event) {
            is StatisticsScreenEvents.Last10DaysReport -> {
                get10DaysReport()
            }
            is StatisticsScreenEvents.Last10WeeksReport -> {
                get10WeeksReport()
            }
            is StatisticsScreenEvents.Last10MonthsReport -> {
                get10MonthsReport()
            }
            is StatisticsScreenEvents.Last10YearsReport -> {
                get10YearsReport()
            }
        }
    }

    private fun get10YearsReport() {
        viewModelScope.launch {
            useCases.getLast10YearsReportUseCase.invoke().collect {
            }
        }
    }

    private fun get10MonthsReport() {
        viewModelScope.launch {
            useCases.getLast10MonthsReportUseCase.invoke().collect {
            }
        }
    }

    private fun get10WeeksReport() {
        viewModelScope.launch {
            useCases.getLast10WeeksReportUseCase.invoke().collect {
            }
        }
    }

    private fun get10DaysReport() {
        viewModelScope.launch {
            useCases.getLast10DaysReportUseCase.invoke().collect { _10daysHistory ->
                _state.value = state.value.copy(
                    periodReport = _10daysHistory.map {
                        it
                    }
                )

            }
        }
    }
}
