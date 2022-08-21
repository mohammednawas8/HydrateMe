package com.example.hydrateme.hydrateme.presentation.app_screens.statistcs_screen

import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hydrateme.hydrateme.presentation.app_screens.statistcs_screen.components.GraphCard
import com.example.hydrateme.hydrateme.presentation.app_screens.statistcs_screen.components.PeriodReportRow
import com.example.hydrateme.hydrateme.presentation.app_screens.statistcs_screen.components.TodaySection

@Composable
fun StatisticsScreen(
    viewModel: StatisticsViewModel = hiltViewModel(),
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.height(20.dp))
        PeriodicReportRow()
        
        Spacer(modifier = Modifier.height(20.dp))
        GraphCard(history = emptyList())

        Spacer(modifier = Modifier.height(20.dp))
        TodaySection()
        Spacer(modifier = Modifier.height(10.dp))
    }

}

@Composable
private fun PeriodicReportRow() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        PeriodReportRow(modifier = Modifier.shadow(24.dp, RoundedCornerShape(15.dp))) {

        }
    }
}