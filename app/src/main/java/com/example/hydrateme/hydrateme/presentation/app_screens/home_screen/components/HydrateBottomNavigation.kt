package com.example.hydrateme.hydrateme.presentation.app_screens.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hydrateme.R
import com.example.hydrateme.hydrateme.presentation.app_screens.home_screen.SelectedItem

@Composable
fun HydrateBottomNavigation(
    modifier: Modifier = Modifier,
    onStatisticsClick: () -> Unit,
    onSettingsClick: () -> Unit,
    selectedItem: SelectedItem,
) {

    BottomNavigation(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))

    ) {

        Row(horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()) {
            IconButton(onClick = onStatisticsClick, Modifier.size(20.dp)) {
                Icon(painter = painterResource(id = R.drawable.ic_statistics),
                    contentDescription = "Statistics",
                    Modifier.size(20.dp),
                    tint = if (selectedItem is SelectedItem.STATISTICS) Color.White else Color(
                        0xFFC3DBE6)
                )
            }
            IconButton(onClick = onSettingsClick, Modifier.size(20.dp)) {
                Icon(painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = "Settings",
                    Modifier.size(20.dp),
                    tint = if (selectedItem is SelectedItem.SETTINGS) Color.White else Color(0xFFC3DBE6)
                )
            }
        }

    }

}

@Composable
@Preview
fun PreviewBottomAppBar() {
    Box(modifier = Modifier.background(Color.White), contentAlignment = Alignment.Center) {
        HydrateBottomNavigation(modifier = Modifier
            .fillMaxWidth()
            .height(75.dp), {}, {},SelectedItem.ADD)
    }
}
