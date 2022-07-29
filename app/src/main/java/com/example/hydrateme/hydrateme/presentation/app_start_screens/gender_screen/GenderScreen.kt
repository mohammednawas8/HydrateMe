package com.example.hydrateme.hydrateme.presentation.app_start_screens.gender_screen

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hydrateme.R
import com.example.hydrateme.hydrateme.presentation.app_start_screens.gender_screen.componants.GenderPicker
import com.example.hydrateme.hydrateme.presentation.app_start_screens.util.AppStartEvents
import com.example.hydrateme.hydrateme.presentation.app_start_screens.util.AppStartViewModel
import com.example.hydrateme.hydrateme.presentation.app_start_screens.util.componants.GradientButton
import com.example.hydrateme.hydrateme.presentation.util.Gender
import com.example.hydrateme.hydrateme.presentation.util.NavigationRoute

private val TAG = "GenderScreen"

@Composable
fun GenderScreen(
    navController: NavController,
    viewModel: AppStartViewModel,
    errorMessage: String = stringResource(id = R.string.gender_error),
    context: Context = LocalContext.current
) {
    val chosenGender = viewModel.userState.value.gender

    val maleScale = animateFloatAsState(
        targetValue = if (chosenGender == Gender.Male.gender) 1.2f else 1f,
        tween(durationMillis = 500)
    )

    val femaleScale = animateFloatAsState(
        targetValue = if (chosenGender == Gender.Female.gender) 1.2f else 1f,
        tween(durationMillis = 1000)
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(80.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_genders),
                contentDescription = "Choose gender",
                modifier = Modifier.size(150.dp),
            )

            Spacer(modifier = Modifier.height(15.dp))

            //Gradient Text
            Text(
                text = stringResource(id = R.string.choose_gender),
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .graphicsLayer(alpha = 0.99f)
                    .drawWithCache {
                        val brush = Brush.linearGradient(
                            listOf(
                                Color(0xFF1BAEEE),
                                Color(0xFFFF4593)
                            )
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(brush, blendMode = BlendMode.SrcAtop)
                        }
                    },
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(60.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {

                GenderPicker(
                    gender = Gender.Male,
                    onClick = {
                        viewModel.onEvent(AppStartEvents.GenderChange(Gender.Male.gender))
                    },
                    isHappy = chosenGender == Gender.Male.gender,
                    modifier = Modifier.scale(maleScale.value)
                )

                Spacer(modifier = Modifier.width(25.dp))

                GenderPicker(
                    gender = Gender.Female,
                    onClick = {
                        viewModel.onEvent(AppStartEvents.GenderChange(Gender.Female.gender))
                    },
                    isHappy = chosenGender == Gender.Female.gender,
                    modifier = Modifier.scale(femaleScale.value)
                )
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 25.dp, start = 16.dp, end = 16.dp)
                .align(BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GradientButton(
                text = null,
                icon = painterResource(id = R.drawable.ic_arrow_back),
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp)
            ) {
                navController.navigateUp()
            }

            GradientButton(
                text = stringResource(id = R.string.next),
                icon = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp)
            ) {
                //Here i just broke the rule of mvvm because it's over killing and it doesn't
                //worth to have additional classes to achieve a simple functionality

                if (chosenGender.isEmpty())
                    Toast.makeText(
                        context,
                        errorMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                else
                    navController.navigate(NavigationRoute.WeightScreen.route)

            }
        }

    }

}