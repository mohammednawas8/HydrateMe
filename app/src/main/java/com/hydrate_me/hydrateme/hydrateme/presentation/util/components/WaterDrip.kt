package com.hydrate_me.hydrateme.hydrateme.presentation.util.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hydrate_me.hydrateme.R
import com.hydrate_me.hydrateme.hydrateme.presentation.util.Gender


@Composable
fun WaterDrip(
    modifier: Modifier = Modifier,
    gender: Gender,
    happy: Boolean,
    sleepy: Boolean = false,
) {

    if (sleepy) {
        if(gender is Gender.Male){
            Image(
                painter = painterResource(id = R.drawable.blue_sleepy),
                contentDescription = "Male drip",
                modifier = modifier,
            )
        }else{
            Image(
                painter = painterResource(id = R.drawable.pink_sleepy),
                contentDescription = "Female drip",
                modifier = modifier,
            )
        }
    } else {
        if (gender is Gender.Male) {
            if (happy)
                Image(
                    painter = painterResource(id = R.drawable.blue_happy_drip),
                    contentDescription = "Male drip",
                    modifier = modifier,
                )
            else
                Image(
                    painter = painterResource(id = R.drawable.blue_sad_drip),
                    contentDescription = "Male drip",
                    modifier = modifier
                )
        } else {
            if (happy)
                Image(
                    painter = painterResource(id = R.drawable.pink_happy_drip),
                    contentDescription = "Male drip",
                    modifier = modifier
                )
            else
                Image(
                    painter = painterResource(id = R.drawable.pink_sad_drip),
                    contentDescription = "Male drip",
                    modifier = modifier
                )
        }
    }
}

@Composable
@Preview
fun PreviewBlueDrip() {
    Row {
        WaterDrip(
            gender = Gender.Male,
            happy = true,
            modifier = Modifier.size(100.dp)
        )
        WaterDrip(
            gender = Gender.Male,
            happy = false,
            modifier = Modifier.size(100.dp)
        )
    }
}

@Composable
@Preview
fun PreviewPinkDrip() {
    Row {
        WaterDrip(
            gender = Gender.Female,
            happy = true,
            modifier = Modifier.size(100.dp)
        )
        WaterDrip(
            gender = Gender.Female,
            happy = false,
            modifier = Modifier.size(100.dp)
        )
    }
}

@Composable
@Preview
fun PreviewSleepyDrip() {
    Row {
        WaterDrip(
            gender = Gender.Male,
            happy = true,
            modifier = Modifier.size(100.dp),
            sleepy = true
        )
        WaterDrip(
            gender = Gender.Female,
            happy = false,
            modifier = Modifier.size(100.dp),
            sleepy = true
        )
    }
}