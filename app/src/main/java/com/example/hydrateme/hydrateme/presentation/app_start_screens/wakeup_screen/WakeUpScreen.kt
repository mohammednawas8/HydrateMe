package com.example.hydrateme.hydrateme.presentation.app_start_screens.wakeup_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.hydrateme.R
import com.example.hydrateme.hydrateme.presentation.app_start_screens.util.AppStartEvents
import com.example.hydrateme.hydrateme.presentation.app_start_screens.util.AppStartViewModel
import com.example.hydrateme.hydrateme.presentation.app_start_screens.util.componants.PickerScreens
import com.example.hydrateme.hydrateme.presentation.util.Gender
import com.example.hydrateme.hydrateme.presentation.util.NavigationRoute

@Composable
fun WakeUpScreen(
    navController: NavController,
    viewModel: AppStartViewModel
) {

    val user = viewModel.userState.value
    val isMale = remember {
        user.gender == Gender.Male.gender
    }

    val leftList = remember {
        (0..24).map { String.format("%02d", it) }.toMutableList()
    }
    val rightList = remember {
        (0..59).map { String.format("%02d", it) }.toMutableList()
    }

    PickerScreens(
        gender = if (isMale) Gender.Male else Gender.Female,
        image = painterResource(id = if (isMale) R.drawable.ic_blue_wakeup else R.drawable.ic_pink_wakeup),
        text = stringResource(id = R.string.wakeup_time),
        time = true,
        leftList = leftList,
        rightList = rightList,
        onLeftValueChange = {
            viewModel.onEvent(AppStartEvents.WakeUpHourChange(it))
        },
        onRightValueChange = {
            viewModel.onEvent(AppStartEvents.WakeUpMinutesChange(it))

        },
        onBackClick = {
            navController.navigateUp()
        },
        onNextClick = {
            navController.navigate(NavigationRoute.SleepScreen.route)
        },
        leftInitial = leftList.indexOf(user.wakeUpHour),
        rightInitial = leftList.indexOf(user.wakeUpMinutes)
    )
}