package com.example.hydrateme.hydrateme.presentation.app_start_screens.introduction_screen.components

import android.graphics.Typeface
import android.text.TextPaint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeSection(
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier.background(Color.Red),
        contentAlignment = Alignment.BottomCenter
    ) {
        val maxWidth = maxWidth
        val maxHeight = maxWidth
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Box(modifier = Modifier) {
                WaterDripWelcome(
                    modifier = Modifier
                        .background(Color.Gray)
                        .width(maxWidth.div(2))
                        .height(maxHeight / 1.2f)
                        .scale(1.2f)
                        .rotate(25f)
                        .offset(x = (-30).dp, y = 30.dp)
                )
            }


//+ 15 * 1.2f
            Box(modifier = Modifier.height(maxHeight)) {
                WaterDripWelcome(modifier = Modifier
                    .background(Color.Green)
                    .graphicsLayer {
                        rotationY = 180f
                    }
                    .height(maxHeight)
                    .width(maxWidth.div(2))
                    .scale(1.2f)
                    .offset(x = (-40).dp, y = 13.dp)
                    .rotate(28f)
                )

            }
        }



    }
}

@Composable
fun MainFunc(
    modifier: Modifier = Modifier,
    welcomeText: String = "Hi",
    color: Color = MaterialTheme.colors.primary,
    shadow: Float = 120f,
    typeface: Typeface,
){
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
                        size = Size(width * 0.17f, height * 0.2f)
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
                        size = Size(45f, 23f)
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
                    this.typeface = typeface //Text font
                }
            )

        }
}



