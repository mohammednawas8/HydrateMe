package com.loc.hydrateme.hydrateme.presentation.app_start_screens.util.componants

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomShadow(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val width = this.size.width
        val height = this.size.height
        drawOval(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0xFFDFDCDC),
                    Color(0x1AE6E6E6),
                ),
                radius = 120f,
                center = center
            ),
            topLeft = Offset(width * 0.12f, 0f),
            size = Size(width, height),
        )
    }
}

@Preview
@Composable
fun PreviewShadow() {
    BottomShadow(
        modifier = Modifier
            .width(80.dp)
            .height(16.dp)
            .background(Color.White)
    )
}