package com.example.hydrateme.hydrateme.presentation.app_screens.home_screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hydrateme.hydrateme.presentation.app_screens.drink_screen.DrinkScreen
import com.example.hydrateme.hydrateme.presentation.app_screens.statistcs_screen.StatisticsScreen
import com.example.hydrateme.hydrateme.presentation.util.NavigationRoute

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    var state = viewModel.state.value
    val homeNavController = rememberNavController()

    var showBottomBar by remember {
        mutableStateOf(true)
    }

    Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
        NavHost(
            navController = homeNavController,
                startDestination = NavigationRoute.DrinkScreen.route,
            modifier = Modifier
                .fillMaxSize()
        ) {
            composable(NavigationRoute.DrinkScreen.route) {
                showBottomBar = true
                DrinkScreen(navController = homeNavController)
            }
            composable(NavigationRoute.StatisticsScreen.route) {
                showBottomBar = false
                StatisticsScreen(navController = homeNavController)
            }
            composable(NavigationRoute.SettingsScreen.route) {
                showBottomBar = false
//                DrinkScreen()
            }
        }
    }
}


