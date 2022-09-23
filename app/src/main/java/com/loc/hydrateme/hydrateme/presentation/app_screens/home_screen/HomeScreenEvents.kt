package com.loc.hydrateme.hydrateme.presentation.app_screens.home_screen

sealed class HomeScreenEvents {
    object StatisticsSelected : HomeScreenEvents()
    object AddSelected : HomeScreenEvents()
    object SettingsSelected : HomeScreenEvents()
}
