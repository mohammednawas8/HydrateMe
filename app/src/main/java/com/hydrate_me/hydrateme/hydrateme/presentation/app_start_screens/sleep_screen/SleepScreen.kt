package com.hydrate_me.hydrateme.hydrateme.presentation.app_start_screens.sleep_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.hydrate_me.hydrateme.R
import com.hydrate_me.hydrateme.hydrateme.presentation.app_start_screens.util.AppStartEvents
import com.hydrate_me.hydrateme.hydrateme.presentation.app_start_screens.util.AppStartViewModel
import com.hydrate_me.hydrateme.hydrateme.presentation.app_start_screens.util.componants.PickerScreens
import com.hydrate_me.hydrateme.hydrateme.presentation.util.Gender
import com.hydrate_me.hydrateme.hydrateme.presentation.util.NavigationRoute
import java.util.*

@SuppressLint("UnspecifiedImmutableFlag")
@Composable
fun SleepScreen(
    navController: NavController,
    viewModel: AppStartViewModel,
) {
    val context = LocalContext.current
    val user = viewModel.userState.value
    val isMale = remember {
        user.gender == Gender.Male.gender
    }

    var leftList by remember {
        mutableStateOf((1..24).map { String.format(Locale.ENGLISH, "%02d", it) }.toMutableList())
    }

    val rightList = remember {
        (0..59).map { String.format(Locale.ENGLISH, "%02d", it) }.toMutableList()
    }

    val leftInitial by remember {
        mutableStateOf(leftList.indexOf(user.bedHour))
    }

    val rightInitial by remember {
        mutableStateOf(rightList.indexOf(user.bedMinutes))
    }

    PickerScreens(
        gender = if (isMale) Gender.Male else Gender.Female,
        image = painterResource(id = if (isMale) R.drawable.ic_blue_sleep else R.drawable.ic_pink_sleep),
        text = "When do you go to bed",
        time = true,
        sleepy = true,
        leftList = leftList,
        rightList = rightList,
        onLeftValueChange = {
            viewModel.onEvent(AppStartEvents.BedHourChange(it))
        },
        onRightValueChange = {
            viewModel.onEvent(AppStartEvents.BedMinutesChange(it))
        },
        onBackClick = {
            navController.navigateUp()
        },
        onNextClick = {

            viewModel.onEvent(AppStartEvents.Finish)
            navController.navigate(NavigationRoute.HomeScreen.route) {
                popUpTo(0)
            }
        },
        leftInitial = if(leftInitial == -1) user.wakeUpHour.toInt() else leftInitial,
        rightInitial = if(rightInitial == -1) user.wakeUpMinutes.toInt() else rightInitial,
    )
}