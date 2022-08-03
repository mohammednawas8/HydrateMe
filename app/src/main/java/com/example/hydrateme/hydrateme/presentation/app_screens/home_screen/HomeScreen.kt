package com.example.hydrateme.hydrateme.presentation.app_screens.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hydrateme.hydrateme.presentation.app_screens.home_screen.components.HydrateBottomNavigation
import com.example.hydrateme.hydrateme.presentation.app_screens.home_screen.components.TopAppBar
import com.example.hydrateme.R
import com.example.hydrateme.hydrateme.presentation.app_screens.drink_screen.DrinkScreen
import com.example.hydrateme.hydrateme.presentation.util.NavigationRoute

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    var state = viewModel.state.value
    val homeNavController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopAppBar(text = state.title)
        },
        bottomBar = {
            HydrateBottomNavigation(
                onStatisticsClick = {
                    viewModel.onEvent(HomeScreenEvents.StatisticsSelected)
//                    TODO("Navigate to the statistics screen")
//                    TODO("Change the appbar text")
                }, onSettingsClick = {
                    viewModel.onEvent(HomeScreenEvents.SettingsSelected)
//                    TODO("Navigate to the settings screen")
//                    TODO("Change the appbar text")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                    .height(70.dp)
                    .shadow(8.dp, RoundedCornerShape(20.dp)),
                selectedItem = state.selectedItem
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(HomeScreenEvents.AddSelected)
//                TODO("Navigate to the drink screen")
//                TODO("Change the appbar text")
            }, backgroundColor = MaterialTheme.colors.primary) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "Add",
                    tint = if (state.selectedItem is SelectedItem.ADD) Color.White else Color(
                        0xFFC3DBE6)
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = homeNavController,
            startDestination = NavigationRoute.DrinkScreen.route,
            modifier = Modifier
                .padding(bottom = bottomPadding + 40.dp)
                .fillMaxSize()) {
            composable(NavigationRoute.DrinkScreen.route) {
                DrinkScreen()
            }
            composable(NavigationRoute.StatisticsScreen.route) {
//                DrinkScreen()
            }
            composable(NavigationRoute.SettingsScreen.route) {
//                DrinkScreen()
            }
        }
    }
}

