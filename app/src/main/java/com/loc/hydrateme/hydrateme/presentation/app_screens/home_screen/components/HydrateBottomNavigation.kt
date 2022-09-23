package com.loc.hydrateme.hydrateme.presentation.app_screens.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loc.hydrateme.R
import com.loc.hydrateme.hydrateme.presentation.app_screens.home_screen.SelectedItem
import com.loc.hydrateme.ui.theme.Blue750
import com.loc.hydrateme.ui.theme.HydrateMeTheme

@Composable
fun HydrateBottomNavigation(
    modifier: Modifier = Modifier,
    onStatisticsClick: () -> Unit,
    onSettingsClick: () -> Unit,
    selectedItem: SelectedItem,
) {

    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
                .padding(start = 16.dp, end = 16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {

            Row(horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(22.dp))
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Blue750)

            ) {
                IconButton(onClick = onStatisticsClick, Modifier.size(50.dp)) {
                    Icon(painter = painterResource(id = R.drawable.ic_statistics),
                        contentDescription = "Statistics",
                        Modifier.size(20.dp),
                        tint = if (selectedItem is SelectedItem.STATISTICS) Color.White else Color(
                            0xFFC3DBE6)
                    )
                }
                IconButton(onClick = onSettingsClick, Modifier.size(50.dp)) {
                    Icon(painter = painterResource(id = R.drawable.ic_settings),
                        contentDescription = "Settings",
                        Modifier.size(20.dp),
                        tint = if (selectedItem is SelectedItem.SETTINGS) Color.White else Color(
                            0xFFC3DBE6)
                    )
                }
            }
            FloatingActionButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.TopCenter),
                backgroundColor = Blue750,
                elevation = FloatingActionButtonDefaults.elevation()
            ) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = "Drink screen",
                    tint = Color.White)
            }
        }
    }
}
@Composable
@Preview
fun PreviewBottomAppBar() {
    HydrateMeTheme {
        HydrateBottomNavigation(modifier = Modifier,{}, {}, SelectedItem.ADD)
    }
}

