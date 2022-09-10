package com.example.hydrateme.hydrateme.presentation.app_screens.settings_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hydrateme.R
import com.example.hydrateme.hydrateme.presentation.app_screens.home_screen.components.WavesTopAppBar
import com.example.hydrateme.hydrateme.presentation.app_screens.settings_screen.components.*
import com.example.hydrateme.hydrateme.presentation.util.Gender
import com.example.hydrateme.hydrateme.presentation.util.NavigationRoute

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    var changeGenderDialog by remember {
        mutableStateOf(false)
    }
    var changeWeightDialog by remember {
        mutableStateOf(false)
    }
    var changeWakeupTimeDialog by remember {
        mutableStateOf(false)
    }
    var changeBedTimeDialog by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            WavesTopAppBar(text = stringResource(id = R.string.settings), true) {
                navController.navigateUp()
            }

            Column(Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                .padding(bottom = 20.dp, top = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)) {

                ReminderSection(
                    reminderScheduleClick = { navController.navigate(NavigationRoute.ReminderScheduleScreen.route) },
                    reminderSoundClick = {})

                PersonalSection(personalInformation = state,
                    genderClick = {
                        changeGenderDialog = true
                    },
                    weightClick = {
                        changeWeightDialog = true
                    },
                    wakeupClick = {
                        changeWakeupTimeDialog = true
                    },
                    sleepClick = {
                        changeBedTimeDialog = true
                    }
                )

                if (changeGenderDialog) {
                    ChangeGenderDialog(
                        onDismissRequest = { changeGenderDialog = false },
                        onSaveClick = {
                            viewModel.onEvent(SettingsScreenEvents.UpdateGender(it))
                            changeGenderDialog = false
                        },
                        gender = if (state.gender == Gender.Male.gender) Gender.Male else Gender.Female)
                }

                if (changeWeightDialog) {
                    ChangeWeightDialog(weight = state.weight,
                        weightUnit = state.unit.weightUnit,
                        onDismissRequest = { changeWeightDialog = false },
                        onCancelClick = { changeWeightDialog = false },
                        onSaveClick = { newWeight, newUnit ->
                            viewModel.onEvent(SettingsScreenEvents.UpdateWeight(newWeight, newUnit))
                            changeWeightDialog = false
                        })
                }

                if (changeWakeupTimeDialog) {
                    ChangeTimeDialog(text = stringResource(id = R.string.wakeup_time),
                        initialHour = state.wakeupHour,
                        initialMinute = state.wakeupMinute,
                        onDismissRequest = { changeWakeupTimeDialog = false },
                        onCancelClick = { changeWakeupTimeDialog = false },
                        onSaveClick = { hour, minute ->
                            viewModel.onEvent(SettingsScreenEvents.UpdateWakeupTime(hour.toString(),
                                minute.toString()))
                            changeWakeupTimeDialog = false
                        })
                }

                if (changeBedTimeDialog) {
                    ChangeTimeDialog(text = stringResource(id = R.string.bed_time),
                        initialHour = state.bedHour,
                        initialMinute = state.bedMinute,
                        onDismissRequest = { changeBedTimeDialog = false },
                        onCancelClick = { changeBedTimeDialog = false },
                        onSaveClick = { hour, minute ->
                            viewModel.onEvent(SettingsScreenEvents.UpdateBedTime(hour.toString(),
                                minute.toString()))
                            changeBedTimeDialog = false
                        })
                }

                OtherSection(resetData = {},
                    feedbackClick = {},
                    shareClick = {},
                    privacyPolicyClick = {})

            }
        }
    }
}

@Composable
private fun ReminderSection(
    reminderScheduleClick: (SettingItem) -> Unit,
    reminderSoundClick: (SettingItem) -> Unit,
) {
    Card(elevation = 0.dp) {
        Column(modifier = Modifier.padding(8.dp)) {
            SettingCard(text = stringResource(id = R.string.reminder_settings))
            SettingItem(settingItem = SettingItem(stringResource(id = R.string.reminder_schedule),
                ""),
                onClick = reminderScheduleClick)
            SettingItem(settingItem = SettingItem(stringResource(id = R.string.reminder_sound),
                ""),
                onClick = reminderSoundClick)
        }
    }
}

@Composable
private fun PersonalSection(
    personalInformation: SettingsState,
    genderClick: (SettingItem) -> Unit,
    weightClick: (SettingItem) -> Unit,
    wakeupClick: (SettingItem) -> Unit,
    sleepClick: (SettingItem) -> Unit,
) {
    Card(elevation = 0.dp) {
        Column(modifier = Modifier.padding(8.dp)) {
            SettingCard(text = stringResource(id = R.string.personal_information))
            SettingItem(settingItem = SettingItem(stringResource(id = R.string.gender),
                personalInformation.gender),
                onClick = genderClick)
            SettingItem(settingItem = SettingItem(stringResource(id = R.string.weight),
                "${personalInformation.weight} ${personalInformation.unit.weightUnit}"),
                onClick = weightClick)
            SettingItem(settingItem = SettingItem(stringResource(id = R.string.wakeup),
                personalInformation.wakeupTime),
                onClick = wakeupClick)
            SettingItem(settingItem = SettingItem(stringResource(id = R.string.bed_time),
                personalInformation.bedTime), onClick = sleepClick)
        }
    }
}

@Composable
private fun OtherSection(
    resetData: (SettingItem) -> Unit,
    feedbackClick: (SettingItem) -> Unit,
    shareClick: (SettingItem) -> Unit,
    privacyPolicyClick: (SettingItem) -> Unit,
) {
    Card(elevation = 0.dp) {
        Column(modifier = Modifier.padding(8.dp)) {
            SettingCard(text = stringResource(id = R.string.other))
            SettingItem(settingItem = SettingItem(stringResource(id = R.string.reset_data),
                ""),
                onClick = resetData)
            SettingItem(settingItem = SettingItem(stringResource(id = R.string.feedback),
                ""),
                onClick = feedbackClick)
            SettingItem(settingItem = SettingItem(stringResource(id = R.string.share),
                ""),
                onClick = shareClick)

            SettingItem(settingItem = SettingItem(stringResource(id = R.string.privacy_policy),
                ""),
                onClick = privacyPolicyClick)
        }
    }
}