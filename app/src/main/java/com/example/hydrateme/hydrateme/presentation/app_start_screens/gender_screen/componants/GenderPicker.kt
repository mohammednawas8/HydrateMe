package com.example.hydrateme.hydrateme.presentation.app_start_screens.gender_screen.componants

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hydrateme.R
import com.example.hydrateme.hydrateme.presentation.app_start_screens.util.componants.BottomShadow
import com.example.hydrateme.hydrateme.presentation.util.Gender
import com.example.hydrateme.hydrateme.presentation.util.components.WaterDrip

@Composable
fun GenderPicker(
    modifier: Modifier = Modifier,
    gender: Gender,
    onClick: () -> Unit,
    isHappy: Boolean,
    dripSize: Dp = 100.dp,
) {
    Column(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick()
            }
            .then(modifier)
    ) {
        WaterDrip(
            gender = gender,
            happy = isHappy,
            modifier = Modifier.size(dripSize)
        )
        Spacer(modifier = Modifier.height(5.dp))
        BottomShadow(
            modifier = Modifier
                .height(dripSize / 6)
                .width(dripSize.div(1.25f))
        )
        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = stringResource(id = if (gender is Gender.Male) R.string.male else R.string.female),
            style = MaterialTheme.typography.h3,
            color = if (isHappy) {
                if (gender is Gender.Male)
                    Color(0xFF1BAEEE)
                else
                    Color(0xFFFF4593)
            } else {
                if (gender is Gender.Male)
                    Color(0xB31BAEEE)
                else
                    Color(0x80FF4593)

            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
