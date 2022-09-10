package com.example.hydrateme.hydrateme.presentation.app_screens.settings_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hydrateme.R
import androidx.compose.ui.unit.sp
import com.example.hydrateme.ui.theme.*

@Composable
fun SettingCard(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Gray400,
    lineColor: Color = Gray150,
    icon: Painter? = null,
) {
    Box(
        modifier = modifier
    ) {
        Column {
            Text(text = text,
                style = MaterialTheme.typography.h2,
                fontSize = 14.sp,
                color = textColor,
            fontWeight = FontWeight.Medium)

            Spacer(modifier = Modifier.height(5.dp))

            Box(modifier = Modifier
                .height(1.dp)
                .background(lineColor)
                .width(180.dp))
        }
    }
}

@Preview
@Composable
fun PreviewSettingCard() {
    HydrateMeTheme {
        SettingCard(text = "General",
            modifier = Modifier.height(40.dp))
    }
}