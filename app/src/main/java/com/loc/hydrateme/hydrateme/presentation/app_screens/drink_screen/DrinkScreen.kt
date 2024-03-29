package com.loc.hydrateme.hydrateme.presentation.app_screens.drink_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.loc.hydrateme.R
import com.loc.hydrateme.hydrateme.presentation.app_screens.drink_screen.components.CupPickerDialog
import com.loc.hydrateme.hydrateme.presentation.app_screens.drink_screen.components.WaterBottle
import com.loc.hydrateme.hydrateme.presentation.app_screens.home_screen.SelectedItem
import com.loc.hydrateme.hydrateme.presentation.app_screens.home_screen.components.HydrateBottomNavigation
import com.loc.hydrateme.hydrateme.presentation.app_screens.home_screen.components.WavesTopAppBar
import com.loc.hydrateme.hydrateme.presentation.util.NavigationRoute
import com.loc.hydrateme.ui.theme.Gray200
import com.loc.hydrateme.ui.theme.HydrateMeTheme
import com.loc.hydrateme.ui.theme.OpenSans

@Composable
fun DrinkScreen(
    viewModel: DrinkViewModel = hiltViewModel(),
    navController: NavController,
) {

    val state = viewModel.state.value
    var showCupPicker by remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            WavesTopAppBar(text = state.date, false) {}

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.padding(top = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(70.dp),
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(id = R.string.daily_goal),
                            style = MaterialTheme.typography.h2
                        )
                        Text(
                            text = state.dailyGoal.toString(),
                            style = MaterialTheme.typography.h2,
                            color = Gray200
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(id = R.string.complete),
                            style = MaterialTheme.typography.h2
                        )
                        Text(
                            text = "${state.complete} ml",
                            style = MaterialTheme.typography.h2,
                            color = Gray200
                        )
                    }
                }

                WaterBottle(
                    drinkAmount = state.complete,
                    waterPercentage = if (state.waterPercentage == 0f) 0.05f else state.waterPercentage,
                    modifier = Modifier
                        .width(130.dp)
                        .height(385.dp)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) {
                            showCupPicker = true
                        },
                    lightColor = false,
                )
            }
            Button(
                onClick = {
                    viewModel.onEvent(DrinkScreenEvents.Drink)
                },
                modifier = Modifier
                    .width(240.dp)
                    .height(50.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = MaterialTheme.colors.primary
                ),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 15.dp,
                    pressedElevation = 5.dp,
                    disabledElevation = 0.dp
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontSize = 24.sp)) {
                                append("+ ")
                            }
                            withStyle(SpanStyle(fontSize = 20.sp)) {
                                append(stringResource(id = R.string.drink))
                            }
                        }, fontFamily = OpenSans, color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
            HydrateBottomNavigation(onStatisticsClick = {
                navController.navigate(NavigationRoute.StatisticsScreen.route)
            },
                onSettingsClick = {
                    navController.navigate(NavigationRoute.SettingsScreen.route)
                },
                selectedItem = SelectedItem.ADD,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 14.dp)
            )
        }

        if (showCupPicker){
            CupPickerDialog(cupList = cupList, onDismissRequest = {showCupPicker = false}) {
                viewModel.onEvent(DrinkScreenEvents.ChangeCupSize(it))
                showCupPicker = false
            }
        }

    }
}

@Composable
@Preview
fun PreviewDrinkScreen() {
    HydrateMeTheme {
        DrinkScreen(navController = rememberNavController())
    }
}