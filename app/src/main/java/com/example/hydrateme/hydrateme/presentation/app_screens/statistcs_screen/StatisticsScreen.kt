package com.example.hydrateme.hydrateme.presentation.app_screens.statistcs_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun StatisticsScreen(
    viewModel: StatisticsViewModel = hiltViewModel()
){
    Box(modifier = Modifier.fillMaxSize()){
        viewModel
    }

}