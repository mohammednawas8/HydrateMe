package com.loc.hydrateme.hydrateme.presentation.app_start_screens.introduction_screen.components

import android.text.TextPaint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loc.hydrateme.R
import com.loc.hydrateme.hydrateme.presentation.util.Gender
import com.loc.hydrateme.hydrateme.presentation.util.components.WaterDrip

@Composable
fun WelcomeSection(
    modifier: Modifier = Modifier,
    shadow: Float = 110f,
    welcomeText: String = "Hi",
    color: Color = MaterialTheme.colors.primary,

    ) {

    BoxWithConstraints(modifier = modifier) {
        val height = this.maxHeight
        val width = this.maxWidth
        val maxHeight = constraints.maxHeight
        val maxWidth = constraints.maxWidth


        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            val leftArmPathLeftDrip = Path().apply {
                moveTo(canvasWidth * 0.15f, canvasHeight * 0.7f)
                lineTo(canvasWidth * 0.3f, canvasHeight * 0.9f)
            }

            val rightArmPathLeftDrip = Path().apply {
                moveTo(canvasWidth / 2f, canvasHeight * 0.95f)
                lineTo(canvasWidth * 0.3f, canvasHeight * 0.9f)
            }

            val leftArmPathRightDrip = Path().apply {
                moveTo(canvasWidth * 0.78f, canvasHeight * 0.7f)
                lineTo(canvasWidth * 0.7f, canvasHeight * 0.9f)
            }

            val rightArmPathRightDrip = Path().apply {
                moveTo(canvasWidth / 2f, canvasHeight * 0.95f)
                lineTo(canvasWidth * 0.7f, canvasHeight * 0.9f)
            }

            drawPath(
                path = leftArmPathRightDrip,
                brush = Brush.linearGradient(
                    listOf(
                        Color.DarkGray,
                        Color.Black
                    )
                ),
                style = Stroke(canvasHeight * 0.035f),
            )

            drawPath(
                path = rightArmPathRightDrip,
                brush = Brush.linearGradient(
                    listOf(
                        Color.DarkGray,
                        Color.Black
                    )
                ),
                style = Stroke(canvasHeight * 0.035f),
            )

            drawPath(
                path = leftArmPathLeftDrip,
                brush = Brush.linearGradient(
                    listOf(
                        Color.DarkGray,
                        Color.Black
                    )
                ),
                style = Stroke(canvasHeight * 0.035f),
            )

            drawPath(
                path = rightArmPathLeftDrip,
                brush = Brush.linearGradient(
                    listOf(
                        Color.DarkGray,
                        Color.Black
                    )
                ),
                style = Stroke(canvasHeight * 0.035f),
            )

            //Left drip insertion
            drawCircle(
                brush = Brush.linearGradient(
                    listOf(
                        Color.DarkGray,
                        Color.Black
                    )
                ),
                radius = 9f,
                center = Offset(canvasWidth * 0.3f, canvasHeight * 0.9f)
            )

            //Right drip insertion
            drawCircle(
                brush = Brush.linearGradient(
                    listOf(
                        Color.DarkGray,
                        Color.Black
                    )
                ),
                radius = 9f,
                center = Offset(canvasWidth * 0.7f, canvasHeight * 0.9f)
            )

            //Left hand
            drawCircle(
                color = color,
                radius = 30f,
                center = Offset(canvasWidth * 0.42f, maxHeight * 0.92f)
            )

            //Right hand
            drawCircle(
                color = color,
                radius = 30f,
                center = Offset(canvasWidth * 0.6f, maxHeight * 0.92f)
            )

            drawContext.canvas.apply {
                val textBackgroundPaint = Paint()
                //Add outline shadow for the text background
                val textFrameworkPaint = textBackgroundPaint.asFrameworkPaint()
                textFrameworkPaint.color = Color.White.toArgb()

                textFrameworkPaint.setShadowLayer(
                    shadow,
                    0f,
                    0f,
                    Color.Gray.toArgb()
                )

                //The text background
                drawOval(
                    Rect(
                        offset = Offset(maxWidth / 2.41f, maxHeight.toFloat() * 0.82f),
                        size = Size(canvasWidth * 0.19f, canvasHeight * 0.18f)
                    ),
                    textBackgroundPaint
                )

            }

            //Left finger
            drawOval(
                color = color,
                topLeft = Offset(canvasWidth * 0.40f, maxHeight * 0.89f),
                size = Size(canvasWidth * 0.05f,canvasHeight * 0.05f)
            )

            //Right finger
            drawOval(
                color = color,
                topLeft = Offset(canvasWidth * 0.57f, maxHeight * 0.89f),
                size = Size(canvasWidth * 0.05f,canvasHeight * 0.05f)
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopStart)
        ) {
            Box(
                modifier = Modifier
                    .height(height * 0.7f)
                    .width(width * 0.46f)
            ) {

                WaterDrip(
                    gender = Gender.Male,
                    happy = true,
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(x = (-width * 0.10f), y = height * 0.05f)
                        .rotate(30f)
                        .scale((maxWidth/40f) * 0.053f)
                )

            }

            Box(
                modifier = Modifier
                    .height(height * 0.7f)
                    .width(width * 0.46f)
            ) {
                WaterDrip(
                    gender = Gender.Male,
                    happy = true,
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(x = (width * 0.10f), y = height * 0.05f)
                        .rotate(-30f)
                        .scale((maxWidth/40f) * 0.053f)
                        .graphicsLayer {
                            rotationY = 180f
                        }
                )
            }
        }
        Text(
            text = welcomeText,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 1.dp, start = 4.dp),
            color = color,
            fontSize = 30.sp,
            fontFamily = FontFamily(Font(R.font.koorkinpro)),
            fontWeight = FontWeight.SemiBold
        )

    }

}

@Composable
fun MainFunc(
    modifier: Modifier = Modifier,
    welcomeText: String = "Hi",
    color: Color = MaterialTheme.colors.primary,
    shadow: Float = 120f,
    typeface: Typeface,
) {
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height

        drawContext.canvas.apply {
            val textBackgroundPaint = Paint()
            //Add outline shadow for the text background
            val textFrameworkPaint = textBackgroundPaint.asFrameworkPaint()
            textFrameworkPaint.color = Color.White.toArgb()

            textFrameworkPaint.setShadowLayer(
                shadow,
                0f,
                0f,
                Color.Gray.toArgb()
            )

            //The text background
            drawOval(
                Rect(
                    offset = Offset(width * 0.42f, height * 0.95f),
                    size = Size(width * 0.17f, height * 0.18f)
                ),
                textBackgroundPaint
            )

            val fingerPaint = Paint()
            val fingerFrameworkPaint = fingerPaint.asFrameworkPaint()
            fingerFrameworkPaint.color = color.toArgb()
            fingerFrameworkPaint.setShadowLayer(
                0f,
                0f,
                0f,
                Color.Gray.toArgb()
            )
            //Left finger
            drawOval(
                Rect(
                    offset = Offset(width * 0.4f, height + 10),
                    size = Size(45f, 20f)
                ),
                fingerPaint
            )

            //Right finger
            drawOval(
                Rect(
                    offset = Offset(width * 0.56f, height + 10),
                    size = Size(45f, 23f)
                ),
                fingerPaint
            )
        }

        //Draw the text
        drawContext.canvas.nativeCanvas.drawText(
            welcomeText,
            width * 0.47f,
            height + 50f,
            TextPaint().apply {
                this.color = color.toArgb()
                textSize = height * 0.15f
//                    this.typeface = typeface //Text font
            }
        )

    }
}



