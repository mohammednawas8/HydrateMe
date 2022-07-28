package com.example.hydrateme.hydrateme.presentation.app_start_screens.GenderScreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hydrateme.R
import com.example.hydrateme.hydrateme.presentation.app_start_screens.util.componants.BottomShadow
import com.example.hydrateme.hydrateme.presentation.app_start_screens.util.componants.GradientButton
import com.example.hydrateme.hydrateme.presentation.util.Gender
import com.example.hydrateme.hydrateme.presentation.util.components.WaterDrip

@Composable
fun GenderScreen(
    navController: NavController
) {
    var chosenGender by remember {
        mutableStateOf("")
    }

    val maleScale = animateFloatAsState(
        targetValue = if(chosenGender == Gender.Male.gender) 1.2f else 1f,
        tween(durationMillis = 1000)
    )

    val femaleScale = animateFloatAsState(
        targetValue = if(chosenGender == Gender.Female.gender) 1.2f else 1f,
        tween(durationMillis = 1000)
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
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
                Column(
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource()}
                    ){
                        chosenGender = Gender.Male.gender
                    }.scale(maleScale.value)
                ) {
                    WaterDrip(
                        gender = Gender.Male,
                        happy = chosenGender == Gender.Male.gender,
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    BottomShadow(
                        modifier = Modifier
                            .height(16.dp)
                            .width(80.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = stringResource(id = R.string.male),
                        style = MaterialTheme.typography.h3,
                        color = if (chosenGender == Gender.Male.gender)
                            Color(0xFF1BAEEE)
                        else
                            Color(0xB31BAEEE),
                        modifier = Modifier.align(CenterHorizontally)
                    )
                }

                Spacer(modifier = Modifier.width(25.dp))

                Column(
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource()}
                    ){
                        chosenGender = Gender.Female.gender
                    }.scale(femaleScale.value)
                ) {
                    WaterDrip(
                        gender = Gender.Female,
                        happy = chosenGender == Gender.Female.gender,
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    BottomShadow(
                        modifier = Modifier
                            .height(16.dp)
                            .width(80.dp)
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = stringResource(id = R.string.female),
                        style = MaterialTheme.typography.h3,
                        color = if (chosenGender == Gender.Female.gender)
                            Color(0xFFFF4593)
                        else
                            Color(0x80FF4593),
                        modifier = Modifier.align(CenterHorizontally)
                    )
                }
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
                navController.navigateUp()
            }
        }

    }

}