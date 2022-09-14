package com.hydrate_me.hydrateme.hydrateme.presentation.app_screens.settings_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hydrate_me.hydrateme.hydrateme.presentation.app_screens.settings_screen.SettingItem
import com.hydrate_me.hydrateme.ui.theme.HydrateMeTheme

@Composable
fun SettingItem(
    modifier: Modifier = Modifier,
    settingItem: SettingItem,
    onClick: (SettingItem) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .then(modifier)
            .clickable {
                onClick(settingItem)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = settingItem.text,
            style = MaterialTheme.typography.h3,
            color = Color.DarkGray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )

        Text(text = settingItem.selected,
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.primary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold)
    }
}

@Preview
@Composable
fun PreviewSettingItem() {
    HydrateMeTheme {
        SettingItem(
            settingItem = SettingItem("Gender", "Male"),
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color.White))
    }
}