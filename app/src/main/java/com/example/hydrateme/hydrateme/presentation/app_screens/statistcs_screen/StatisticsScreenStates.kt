package com.example.hydrateme.hydrateme.presentation.app_screens.statistcs_screen

import com.example.hydrateme.hydrateme.domain.model.Day

data class StatisticsScreenStates(
    val periodReport: List<Day> = emptyList(),
    val dailyReport: List<Day> = emptyList()
)
