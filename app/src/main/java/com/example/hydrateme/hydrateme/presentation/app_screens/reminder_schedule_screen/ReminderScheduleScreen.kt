package com.example.hydrateme.hydrateme.presentation.app_screens.reminder_schedule_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hydrateme.hydrateme.presentation.app_screens.reminder_schedule_screen.components.ReminderItem
import com.example.hydrateme.hydrateme.presentation.app_screens.reminder_schedule_screen.components.ReminderScheduleTopBar

@Composable
fun ReminderScheduleScreen(
    navController: NavController,
    viewModel: ReminderScheduleViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        topBar = {
            ReminderScheduleTopBar(onBackClick = { navController.navigateUp() }, onAddClick = {})
        }
    ) {
        LazyColumn() {
            itemsIndexed(state) { index, item ->
                ReminderItem(alarm = item, onCheckChange = { isChecked, alarm ->
                    viewModel.onEvent(ReminderScheduleScreenEvents.SwitchChange(isChecked, alarm))
                })
            }
        }
    }
}