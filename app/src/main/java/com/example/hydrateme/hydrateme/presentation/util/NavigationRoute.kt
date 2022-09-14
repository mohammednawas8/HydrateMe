package com.example.hydrateme.hydrateme.presentation.util

sealed class NavigationRoute(val route: String) {
    object IntroductionScreen: NavigationRoute("Introduction_screen")
    object GenderScreen: NavigationRoute("Gender_screen")
    object WeightScreen: NavigationRoute("Weight_screen")
    object WakeupScreen: NavigationRoute("Wakeup_screen")
    object SleepScreen: NavigationRoute("Sleep_screen")
    object HomeScreen: NavigationRoute("Home_screen")
    object DrinkScreen: NavigationRoute("Drink_screen")
    object StatisticsScreen: NavigationRoute("Statistics_screen")
    object SettingsScreen: NavigationRoute("Settings_screen")
    object SplashScreen: NavigationRoute("splash_screen")
    object ReminderScheduleScreen: NavigationRoute("reminder_schedule_screen")
    object ReminderSoundScreen: NavigationRoute("reminder_sound_screen")
    object PrivacyPolicyScreen: NavigationRoute("privacy_policy_screen")

}
