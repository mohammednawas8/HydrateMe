package com.hydrate_me.hydrateme.hydrateme.presentation.app_screens.home_screen

import java.text.SimpleDateFormat
import java.util.*

data class HomeScreenStates(
    val title: String = SimpleDateFormat("E, MMMM dd", Locale.ENGLISH).format(Date()),
    val selectedItem: SelectedItem = SelectedItem.ADD
)
