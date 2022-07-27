package com.example.hydrateme.hydrateme.presentation.app_start_screens.introduction_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hydrateme.R
import com.example.hydrateme.hydrateme.presentation.util.Gender
import com.example.hydrateme.hydrateme.presentation.util.components.WaterDrip
import com.example.hydrateme.ui.theme.Blue700
import com.example.hydrateme.ui.theme.Gray300

@Composable
fun WaterDripWelcome(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta)) {
            val width = size.width
            val height = size.height


            val leftArmPath = Path().apply {
                moveTo(width * 0.33f, height * 0.4f)
                lineTo(width * 0.83f, height * 0.7f)
            }

            val rightArmPath = Path().apply {
                moveTo(width, height * 0.7f)
                lineTo(width * 0.83f, height * 0.7f)
            }

            drawCircle(
                brush = Brush.linearGradient(
                    listOf(
                        Gray300,
                        Color.Black
                    )
                ),
                radius = height * 0.015f,
                center = Offset(width * 0.83f, height * 0.7f)
            )

            drawPath(
                path = leftArmPath,
                brush = Brush.linearGradient(
                    listOf(
                        Gray300,
                        Color.Black
                    )
                ),
                style = Stroke(height * 0.035f),
            )

            drawPath(
                path = rightArmPath,
                brush = Brush.linearGradient(
                    listOf(
                        Gray300,
                        Color.Black
                    )
                ),
                style = Stroke(height * 0.035f),
            )

            drawCircle(
                color = Blue700,
                center = Offset(width, height * 0.7f),
                radius = height * 0.06f
            )
        }


        Box(contentAlignment = Alignment.CenterStart,
            modifier = Modifier

        ) {
            WaterDrip(
                gender = Gender.Male(),
                happy = true,
                modifier = Modifier.fillMaxWidth(0.86f)
            )
        }

    }
}


@Preview
@Composable
fun WaterDripWelcomeTest() {
    WaterDripWelcome(
        modifier = Modifier.size(300.dp)
    )
}
