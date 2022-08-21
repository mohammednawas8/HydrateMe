package com.example.hydrateme.hydrateme.presentation.app_screens.statistcs_screen.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hydrateme.R
import com.example.hydrateme.ui.theme.Gray150
import com.example.hydrateme.ui.theme.Gray400

@Composable
fun TodaySection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .shadow(5.dp, RoundedCornerShape(10.dp), true),
        shape = RoundedCornerShape(10.dp),
    ) {

        Column() {
            Text(
                text = stringResource(id = R.string.today_record),
                style = MaterialTheme.typography.h2,
                color = Gray400,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Gray150))
            (1..30).forEach {
                Text(text = it.toString())
            }
        }

    }
}