package com.example.hydrateme.hydrateme.presentation.app_screens.statistcs_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hydrateme.R
import com.example.hydrateme.hydrateme.presentation.app_screens.statistcs_screen.TodayItem
import com.example.hydrateme.ui.theme.Gray150
import com.example.hydrateme.ui.theme.Gray300
import com.example.hydrateme.ui.theme.Gray400

@Composable
fun TodaySection(
    items: List<TodayItem>,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .shadow(5.dp, RoundedCornerShape(10.dp), true),
        shape = RoundedCornerShape(10.dp),
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            Text(
                text = stringResource(id = R.string.today_record),
                style = MaterialTheme.typography.h2,
                color = Gray400,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp),
                fontSize = 12.sp
            )

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Gray150))

            items.forEach { item ->
                TodayItem(
                    item = item)
            }
        }

        if (items.isEmpty()) {
            Box(modifier = Modifier.fillMaxWidth().height(80.dp)){
                Text(text = stringResource(id = R.string.no_report),
                    modifier = Modifier.align(Alignment.BottomCenter),
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.h2,
                    color = Gray300)
            }

        }
    }
}

@Composable
fun TodayItem(
    item: TodayItem,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_water_cup),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(23.dp))
            Text(
                text = "${item.amount}${item.unit}",
                style = MaterialTheme.typography.h2,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Text(
            text = item.time,
            fontSize = 12.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.Light
        )
    }
}

@Preview
@Composable
fun PreviewTodaySection() {
    TodaySection(
        items = listOf(
            TodayItem(300, "ml", "12:45am"),
            TodayItem(300, "ml", "12:15am"),
            TodayItem(300, "ml", "11:45pm"),
            TodayItem(300, "ml", "11:15pm"),
            TodayItem(300, "ml", "10:15pm"),
        )
    )
}