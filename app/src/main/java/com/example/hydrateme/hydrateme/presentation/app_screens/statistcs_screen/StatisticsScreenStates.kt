package com.example.hydrateme.hydrateme.presentation.app_screens.statistcs_screen

import com.example.hydrateme.hydrateme.domain.model.History

data class StatisticsScreenStates(
    var periodReport: List<History> = emptyList(),
    var dailyReport: List<TodayItem> = emptyList(),
    val unit: String= "ml",
    val period: String = "Day"
)


