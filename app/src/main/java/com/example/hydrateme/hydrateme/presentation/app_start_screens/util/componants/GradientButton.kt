package com.example.hydrateme.hydrateme.presentation.app_start_screens.util.componants

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hydrateme.ui.theme.Blue650Transparent
import com.example.hydrateme.ui.theme.White

@Composable
fun GradientButton(
    modifier: Modifier = Modifier,
    text: String?,
    textStyle: TextStyle = MaterialTheme.typography.h3,
    icon: Painter?,
    gradient: Brush = Brush.linearGradient(
        listOf(Blue650Transparent, MaterialTheme.colors.primary)
    ),
    contentColor: Color = White,
    onClick: () -> Unit,

    ) {

    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(gradient)
            .clickable { onClick() }
            ,
        contentAlignment = Alignment.Center
    ) {
        if (icon != null) {
            Icon(
                painter = icon,
                contentDescription = text,
                tint = contentColor,
                modifier = Modifier
                    .width(12.dp)
                    .height(24.dp)
            )
        } else {
            Text(
                text = text!!,
                style = textStyle,
                textAlign = TextAlign.Center,
                color = contentColor
            )
        }
    }

}

@Preview
@Composable
fun ButtonPreview() {
    GradientButton(
        modifier = Modifier
            .width(260.dp)
            .height(90.dp),
        text = "LET'S GO",
        icon = null,
    ) {

    }
}