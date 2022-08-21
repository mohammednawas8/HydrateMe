package com.example.hydrateme.hydrateme.presentation.app_screens.drink_screen

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
import com.example.hydrateme.R
import com.example.hydrateme.hydrateme.presentation.app_screens.drink_screen.components.WaterBottle
import com.example.hydrateme.hydrateme.presentation.app_screens.home_screen.SelectedItem
import com.example.hydrateme.hydrateme.presentation.app_screens.home_screen.components.HydrateBottomNavigation
import com.example.hydrateme.hydrateme.presentation.app_screens.home_screen.components.WavesTopAppBar
import com.example.hydrateme.hydrateme.presentation.util.NavigationRoute
import com.example.hydrateme.ui.theme.Gray200
import com.example.hydrateme.ui.theme.HydrateMeTheme
import com.example.hydrateme.ui.theme.OpenSans

@Composable
fun DrinkScreen(
    viewModel: DrinkViewModel = hiltViewModel(),
    navController: NavController
) {

    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            WavesTopAppBar(text = state.date,false) {}

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(50.dp),
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
                        .height(385.dp),
                    lightColor = false
                )
            }
            Button(
                onClick = {
                    viewModel.onEvent(DrinkScreenEvents.Drink(200))
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

    }
}

@Composable
@Preview
fun PreviewDrinkScreen() {
    HydrateMeTheme {
        DrinkScreen(navController = rememberNavController())
    }
}