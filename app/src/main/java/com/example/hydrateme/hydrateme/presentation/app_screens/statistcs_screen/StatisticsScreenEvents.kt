package com.example.hydrateme.hydrateme.presentation.app_screens.statistcs_screen

sealed class StatisticsScreenEvents {
    object Last10DaysReport: StatisticsScreenEvents()
    object Last10WeeksReport: StatisticsScreenEvents()
    object Last10MonthsReport: StatisticsScreenEvents()
    object Last10YearsReport: StatisticsScreenEvents()
}