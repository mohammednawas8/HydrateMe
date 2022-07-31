package com.example.hydrateme.hydrateme.presentation.app_start_screens.util.componants

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hydrateme.R
import com.example.hydrateme.hydrateme.presentation.util.Gender
import com.example.hydrateme.hydrateme.presentation.util.components.WaterDrip
import com.example.hydrateme.ui.theme.Blue700
import com.example.hydrateme.ui.theme.Pink

@Composable
fun PickerScreens(
    modifier: Modifier = Modifier,
    gender: Gender,
    image: Painter,
    text: String,
    time: Boolean,
    leftList: MutableList<String>,
    rightList: MutableList<String>,
    onLeftValueChange: (String) -> Unit,
    onRightValueChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
    leftInitial: Int,
    rightInitial: Int
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(80.dp))

            Image(
                painter = image,
                contentDescription = text,
                modifier = Modifier.size(150.dp),
            )

            Spacer(modifier = Modifier.height(15.dp))

            //Gradient Text
            Text(
                text = text,
                style = MaterialTheme.typography.h3,
                fontSize = 20.sp,
                color = if (gender is Gender.Male) Blue700 else Pink
            )

            Spacer(modifier = Modifier.height(60.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                ) {
                    WaterDrip(
                        gender = gender,
                        happy = true,
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    BottomShadow(
                        modifier = Modifier
                            .height(100.dp / 6)
                            .width(100.dp.div(1.25f))
                    )
                }
                    
                Spacer(modifier = Modifier.width(50.dp))
                
                ScrollPicker(
                    leftList = leftList,
                    rightList = rightList,
                    onLeftValueChange = onLeftValueChange,
                    onRightValueChange = onRightValueChange,
                    time = time,
                    highLightedColor = if (gender is Gender.Male) Blue700 else Pink,
                    modifier = Modifier.width(152.dp),
                    leftInitialItemIndex = leftInitial,
                    rightInitialIndex = rightInitial
                )

            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 25.dp, start = 16.dp, end = 16.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GradientButton(
                text = null,
                icon = painterResource(id = R.drawable.ic_arrow_back),
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp)
            ) {
                onBackClick()
            }

            GradientButton(
                text = stringResource(id = R.string.next),
                icon = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp)
            ) {
                onNextClick()

            }
        }
    }
}
