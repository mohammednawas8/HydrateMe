package com.loc.hydrateme.hydrateme.presentation.app_screens.statistcs_screen

import com.loc.hydrateme.hydrateme.domain.model.History

data class StatisticsScreenStates(
    var periodReport: List<History> = emptyList(),
    var dailyReport: List<TodayItem> = emptyList(),
    val unit: String= "ml",
    val period: String = "Day"
)


