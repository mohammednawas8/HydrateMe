package com.hydrate_me.hydrateme.hydrateme.presentation.app_screens.statistcs_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hydrate_me.hydrateme.hydrateme.domain.use_case.UseCases
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
            is StatisticsScreenEvents.PeriodReport -> {
                when (event.period) {
                    Period.DAYS -> {
                        get10DaysReport()
                    }
                    Period.WEEKS -> {
                        get10WeeksReport()
                    }
                    Period.MONTHS -> {
                        get10MonthsReport()
                    }
                    Period.YEARS -> {
                        get10YearsReport()
                    }
                }
            }
        }
    }

    private fun get10YearsReport() {
        viewModelScope.launch {
            useCases.getLast10YearsReportUseCase.invoke().collect { _10YearsHistory ->
                _state.value = state.value.copy(
                    periodReport = _10YearsHistory.map {
                        it
                    },
                    period = "Year"
                )
            }
        }
    }

    private fun get10MonthsReport() {
        viewModelScope.launch {
            useCases.getLast10MonthsReportUseCase.invoke().collect {
                useCases.getLast10MonthsReportUseCase.invoke().collect { _10MonthsHistory ->
                    _state.value = state.value.copy(
                        periodReport = _10MonthsHistory.map {
                            it
                        },
                        period = "Month"
                    )
                }
            }
        }
    }

    private fun get10WeeksReport() {
        viewModelScope.launch {
            useCases.getLast10WeeksReportUseCase.invoke().collect {
                useCases.getLast10WeeksReportUseCase.invoke().collect { _10WeeksHistory ->
                    _state.value = state.value.copy(
                        periodReport = _10WeeksHistory.map {
                            it
                        },
                        period = "Week"

                    )
                }
            }
        }
    }

    private fun get10DaysReport() {
        viewModelScope.launch {
            useCases.getLast10DaysReportUseCase.invoke().collect { _10daysHistory ->
                _state.value = state.value.copy(
                    periodReport = _10daysHistory.map {
                        it
                    },
                    period = "Day"
                )

            }
        }
    }
}
