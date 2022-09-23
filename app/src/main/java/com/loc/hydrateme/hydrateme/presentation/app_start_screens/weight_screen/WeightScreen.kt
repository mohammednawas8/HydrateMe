package com.loc.hydrateme.hydrateme.presentation.app_start_screens.weight_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.loc.hydrateme.R
import com.loc.hydrateme.hydrateme.presentation.app_start_screens.util.AppStartEvents
import com.loc.hydrateme.hydrateme.presentation.app_start_screens.util.AppStartViewModel
import com.loc.hydrateme.hydrateme.presentation.app_start_screens.util.componants.PickerScreens
import com.loc.hydrateme.hydrateme.presentation.util.Gender
import com.loc.hydrateme.hydrateme.presentation.util.NavigationRoute

@Composable
fun WeightScreen(
    viewModel: AppStartViewModel,
    navController: NavController,
) {
    val user = viewModel.userState.value
    val isMale = remember {
        user.gender == Gender.Male.gender
    }

    var leftList = remember {
        mutableStateOf((40..250).map { it.toString() }.toMutableList())
    }

    val kg = stringResource(id = R.string.kg)
    val ib = stringResource(id = R.string.ib)
    val rightList = remember {
        mutableListOf(kg, ib)
    }

    PickerScreens(
        gender = if (isMale) Gender.Male else Gender.Female,
        image = painterResource(id = if (isMale) R.drawable.ic_blue_weight else R.drawable.ic_pink_weight),
        text = stringResource(id = R.string.weight_text),
        time = false,
        leftList = leftList.value,
        rightList = rightList,
        onLeftValueChange = {
            viewModel.onEvent(AppStartEvents.WeightChange(it.toInt()))
        },
        onRightValueChange = {
            viewModel.onEvent(AppStartEvents.WeightUnitChange(it))
        },
        onBackClick = {
            navController.navigateUp()
        },
        onNextClick = {
            navController.navigate(NavigationRoute.WakeupScreen.route)
        },
        leftInitial = leftList.value.indexOf(user.weight.toString()),
        rightInitial = if (user.weightUnit == kg) 0 else 1,
    )

}