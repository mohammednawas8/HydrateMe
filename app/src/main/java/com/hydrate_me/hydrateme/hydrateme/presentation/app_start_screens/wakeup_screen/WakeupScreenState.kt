package com.hydrate_me.hydrateme.hydrateme.presentation.app_start_screens.wakeup_screen

data class WakeupScreenState(
    var hours: List<Int> = (1..24).map { it },
    var minutes: List<Int> = (0..60).map { it }
) {
}