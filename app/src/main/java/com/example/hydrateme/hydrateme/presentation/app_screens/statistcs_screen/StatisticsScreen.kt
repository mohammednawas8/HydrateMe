package com.example.hydrateme.hydrateme.presentation.app_screens.statistcs_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hydrateme.R
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hydrateme.hydrateme.domain.model.History
import com.example.hydrateme.hydrateme.presentation.app_screens.home_screen.components.WavesTopAppBar
import com.example.hydrateme.hydrateme.presentation.app_screens.statistcs_screen.components.ChartCard
import com.example.hydrateme.hydrateme.presentation.app_screens.statistcs_screen.components.PeriodReportRow
import com.example.hydrateme.hydrateme.presentation.app_screens.statistcs_screen.components.TodaySection

@Composable
fun StatisticsScreen(
    viewModel: StatisticsViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        WavesTopAppBar(text = stringResource(id = R.string.history), true) {
            navController.navigateUp()
        }
        Spacer(modifier = Modifier.height(20.dp))
        PeriodicReportRow()

        Spacer(modifier = Modifier.height(20.dp))
        ChartCard(state.periodReport,state.unit,state.period)

        Spacer(modifier = Modifier.height(20.dp))
        TodaySection(state.dailyReport)
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