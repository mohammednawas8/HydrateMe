package com.example.hydrateme.hydrateme.presentation.app_start_screens.sleep_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.hydrateme.R
import com.example.hydrateme.hydrateme.presentation.app_start_screens.util.AppStartEvents
import com.example.hydrateme.hydrateme.presentation.app_start_screens.util.AppStartViewModel
import com.example.hydrateme.hydrateme.presentation.app_start_screens.util.componants.PickerScreens
import com.example.hydrateme.hydrateme.presentation.util.Gender
import com.example.hydrateme.hydrateme.presentation.util.NavigationRoute

@Composable
fun SleepScreen(
    navController: NavController,
    viewModel: AppStartViewModel,
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
            navController.navigate(NavigationRoute.HomeScreen.route)
        },
        leftInitial = leftList.indexOf(user.bedHour),
        rightInitial = rightList.indexOf(user.bedMinutes)
    )
}