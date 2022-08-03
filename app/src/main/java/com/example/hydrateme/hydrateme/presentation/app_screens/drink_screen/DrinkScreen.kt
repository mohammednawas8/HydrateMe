package com.example.hydrateme.hydrateme.presentation.app_screens.drink_screen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hydrateme.R
import com.example.hydrateme.hydrateme.presentation.app_screens.drink_screen.components.WaterBottle
import com.example.hydrateme.hydrateme.presentation.app_screens.util.componants.WavesAndText
import com.example.hydrateme.ui.theme.Gray200
import com.example.hydrateme.ui.theme.HydrateMeTheme
import com.example.hydrateme.ui.theme.OpenSans

@Composable
fun DrinkScreen(
    viewModel: DrinkViewModel = hiltViewModel(),
) {
    val user = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(6f),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(50.dp),
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(id = R.string.daily_goal),
                            style = MaterialTheme.typography.h2
                        )
                        Text(
                            text = user.dailyGoal.toString(),
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
                            text = "${user.complete} ml",
                            style = MaterialTheme.typography.h2,
                            color = Gray200
                        )
                    }
                }
                var isAnimated by remember {
                    mutableStateOf(false)
                }
                WaterBottle(
                    text = user.complete.toString(),
                    waterPercentage = 0.3f,
                    modifier = Modifier
                        .width(130.dp)
                        .height(335.dp)
                )
                LaunchedEffect(key1 = true) {
                    isAnimated = true
                }
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(240.dp)
                    .height(50.dp)
                    .clip(CircleShape),
                colors = ButtonDefaults.buttonColors(contentColor = Color.White,
                    backgroundColor = MaterialTheme.colors.primary),

            ) {
                Row(modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(text = buildAnnotatedString {
                        withStyle(SpanStyle(fontSize = 24.sp)) {
                        append("+ ")
                        }
                        withStyle(SpanStyle(fontSize = 20.sp)) {
                            append(stringResource(id = R.string.drink))
                        }
                    }, fontFamily = OpenSans, color = Color.White)
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewDrinkScreen() {
    HydrateMeTheme {
        DrinkScreen()
    }
}