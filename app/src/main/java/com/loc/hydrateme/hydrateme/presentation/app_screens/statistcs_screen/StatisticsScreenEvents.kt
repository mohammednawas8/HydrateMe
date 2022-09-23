package com.loc.hydrateme.hydrateme.presentation.app_screens.statistcs_screen

sealed class StatisticsScreenEvents {
    data class PeriodReport(val period: String) : StatisticsScreenEvents()
}

 class Period {
     companion object{
         const val DAYS = "D"
         const val WEEKS = "W"
         const val MONTHS = "M"
         const val YEARS = "Y"
     }
}