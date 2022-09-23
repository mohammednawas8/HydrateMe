package com.loc.hydrateme.hydrateme.presentation.app_screens.home_screen

import android.content.res.Resources
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.loc.hydrateme.R
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val resources: Resources
): ViewModel() {



    private val _state = mutableStateOf(HomeScreenStates())
    val state: State<HomeScreenStates> = _state

    fun onEvent(homeScreenEvents: HomeScreenEvents) {
        when (homeScreenEvents) {
            is HomeScreenEvents.AddSelected -> {
                _state.value = state.value.copy(
                    title = SimpleDateFormat("E, MMMM dd", Locale.ENGLISH).format(Date()),
                    selectedItem = SelectedItem.ADD
                )
            }
            is HomeScreenEvents.SettingsSelected -> {
                _state.value = state.value.copy(
                    title = resources.getString(R.string.settings),
                    selectedItem = SelectedItem.SETTINGS
                )
            }
            is HomeScreenEvents.StatisticsSelected -> {
                _state.value = state.value.copy(
                    title = resources.getString(R.string.history),
                    selectedItem = SelectedItem.STATISTICS
                )
            }
        }
    }
}