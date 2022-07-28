package com.example.hydrateme.hydrateme.presentation.util

sealed class NavigationRoute(val route: String) {
    object IntroductionScreen: NavigationRoute("Introduction_screen")
    object GenderScreen: NavigationRoute("Gender_screen")
    object WeightScreen: NavigationRoute("Weight_screen")
    object WakeupScreen: NavigationRoute("Wakeup_screen")
    object SleepScreen: NavigationRoute("Sleep_screen")
}
