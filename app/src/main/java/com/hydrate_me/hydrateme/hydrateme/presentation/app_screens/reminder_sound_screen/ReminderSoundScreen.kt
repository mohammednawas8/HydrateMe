package com.hydrate_me.hydrateme.hydrateme.presentation.app_screens.reminder_sound_screen

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hydrate_me.hydrateme.R
import com.hydrate_me.hydrateme.hydrateme.presentation.app_screens.reminder_schedule_screen.components.ReminderScheduleTopBar
import com.hydrate_me.hydrateme.hydrateme.presentation.app_screens.reminder_sound_screen.components.SoundItem

@Composable
fun ReminderSoundScreen(
    navController: NavController,
    viewModel: ReminderSoundViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val state = viewModel.state.value

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        topBar = {
            ReminderScheduleTopBar(onBackClick = { navController.navigateUp() }, onAddClick = {},
                stringResource(id = R.string.reminder_sound), false)
        },
        backgroundColor = Color.White
    ) {
        LazyColumn() {
            itemsIndexed(state.soundsList) { i, item ->
                SoundItem(soundItem = item,
                    onItemClick = {
                        viewModel.onEvent(ReminderSoundScreenEvents.ChangeSound(it.id))
                        MediaPlayer.create(context, it.id).start()
                    },
                    isSelected = item.id == state.sound
                )
            }
        }
    }
}