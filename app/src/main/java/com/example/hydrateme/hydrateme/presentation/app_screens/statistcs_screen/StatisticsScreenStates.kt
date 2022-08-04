package com.example.hydrateme.hydrateme.presentation.app_screens.statistcs_screen

import com.example.hydrateme.hydrateme.domain.model.History

data class StatisticsScreenStates(
    val periodReport: List<History> = emptyList(),
    val dailyReport: List<History> = emptyList()
)
