package com.example.hydrateme.hydrateme.presentation.app_start_screens.weight_screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
fun WeightScreen(
    viewModel: AppStartViewModel,
    navController: NavController
) {
    val userState = viewModel.userState.value
    val isMale = remember {
        userState.gender == Gender.Male.gender
    }

    val leftList = remember {
        (40..100).map { it.toString() }.toMutableList()
    }

    val kg = stringResource(id = R.string.kg)
    val ib = stringResource(id = R.string.ib)
    val rightList = remember {
        mutableListOf(ib,kg)
    }
    PickerScreens(
        gender = if (isMale) Gender.Male else Gender.Female,
        image = painterResource(id = if (isMale) R.drawable.ic_blue_weight else R.drawable.ic_pink_weight),
        text = stringResource(id = R.string.weight),
        time = false,
        leftList = leftList,
        rightList = rightList,
        onLeftValueChange = {
            viewModel.onEvent(AppStartEvents.WeightChange(it.toInt()))
        },
        onRightValueChange = {
            viewModel.onEvent(AppStartEvents.WeightUnit(it))
        },
        onBackClick = {
            navController.navigateUp()
        },
        onNextClick = {
            navController.navigate(NavigationRoute.WakeupScreen.route)
        },
        leftInitial = 30,
        rightInitial = 0
    )

}