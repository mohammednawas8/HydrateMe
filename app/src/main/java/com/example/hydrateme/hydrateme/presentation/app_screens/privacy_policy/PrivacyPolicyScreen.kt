package com.example.hydrateme.hydrateme.presentation.app_screens.privacy_policy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hydrateme.R
import com.example.hydrateme.hydrateme.presentation.app_screens.reminder_schedule_screen.components.ReminderScheduleTopBar

@Composable
fun PrivacyPolicyScreen(
    navController: NavController
) {


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        topBar = {
            ReminderScheduleTopBar(onBackClick = { navController.navigateUp() }, onAddClick = {},
                stringResource(id = R.string.privacy_policy), false)
        },
        backgroundColor = Color.White
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(150.dp))

            Text(
                text = stringResource(id = R.string.privacy_policy),
                color = MaterialTheme.colors.primary,
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = stringResource(id = R.string.privacy_policy_text),
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(14.dp))

            PointItem(text = stringResource(id = R.string.internet_permission))
            PointItem(text = stringResource(id = R.string.internet_permission))

            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = stringResource(id = R.string.privacy_policy_text2),
                fontSize = 12.sp
            )

        }
    }

}