package com.loc.hydrateme.hydrateme.presentation.app_screens.reminder_schedule_screen

import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.loc.hydrateme.hydrateme.presentation.app_screens.reminder_schedule_screen.components.ReminderItem
import com.loc.hydrateme.hydrateme.presentation.app_screens.reminder_schedule_screen.components.ReminderScheduleTopBar
import com.loc.hydrateme.R


@Composable
fun ReminderScheduleScreen(
    navController: NavController,
    viewModel: ReminderScheduleViewModel = hiltViewModel(),
) {

    var timePickerDialog by remember {
        mutableStateOf(false)
    }

    val state = viewModel.state.value
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        topBar = {
            ReminderScheduleTopBar(onBackClick = { navController.navigateUp() }, onAddClick = {
                timePickerDialog = true
            }, stringResource(id = R.string.reminder_schedule), true)
        },
        backgroundColor = Color.White
    ) {
        LazyColumn() {
            itemsIndexed(state) { _, item ->
                ReminderItem(alarm = item, onCheckChange = { isChecked, alarm ->
                    viewModel.onEvent(ReminderScheduleScreenEvents.SwitchChange(isChecked, alarm))
                })
            }
        }

        if (timePickerDialog) {
            val timeDialog = TimePickerDialog(
                LocalContext.current,
                { _, hour, min ->
                    viewModel.onEvent(ReminderScheduleScreenEvents.SetAlarm(hour, min))
                    timePickerDialog = false
                },
                13,
                0,
                true
            )
            timeDialog.show()
            timeDialog.setOnCancelListener {
                timePickerDialog = false
            }
        }
    }
}