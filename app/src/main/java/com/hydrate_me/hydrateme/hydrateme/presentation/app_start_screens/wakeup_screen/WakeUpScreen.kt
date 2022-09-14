package com.hydrate_me.hydrateme.hydrateme.presentation.app_start_screens.wakeup_screen

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.hydrate_me.hydrateme.R
import com.hydrate_me.hydrateme.hydrateme.presentation.app_start_screens.util.AppStartEvents
import com.hydrate_me.hydrateme.hydrateme.presentation.app_start_screens.util.AppStartViewModel
import com.hydrate_me.hydrateme.hydrateme.presentation.app_start_screens.util.componants.PickerScreens
import com.hydrate_me.hydrateme.hydrateme.presentation.util.Gender
import com.hydrate_me.hydrateme.hydrateme.presentation.util.NavigationRoute
import java.util.*

@Composable
fun WakeUpScreen(
    navController: NavController,
    viewModel: AppStartViewModel
) {

    val user = viewModel.userState.value
    val isMale = remember {
        user.gender == Gender.Male.gender
    }

    var leftList = viewModel.wakeupScreen.value.hours
        .map { String.format(Locale.ENGLISH,"%02d", it) }.toMutableList()


    val rightList = remember {
        (0..59).map { String.format(Locale.ENGLISH,"%02d", it) }.toMutableList()
    }

    val leftInitial by remember {
        mutableStateOf(leftList.indexOf(user.wakeUpHour))
    }

    val rightInitial by remember {
        mutableStateOf(rightList.indexOf(user.wakeUpMinutes))
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
        leftInitial = if(leftInitial == -1) user.wakeUpHour.toInt() else leftInitial,
        rightInitial = if(rightInitial == -1) user.wakeUpMinutes.toInt() else rightInitial,
    )

}