package com.loc.hydrateme.hydrateme.presentation.app_start_screens.introduction_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.loc.hydrateme.hydrateme.presentation.app_start_screens.introduction_screen.components.WelcomeSection
import com.loc.hydrateme.hydrateme.presentation.app_start_screens.util.componants.GradientButton
import com.loc.hydrateme.hydrateme.presentation.util.NavigationRoute
import com.loc.hydrateme.ui.theme.Fredoka
import com.loc.hydrateme.R

@Composable
fun IntroductionScreen(
    navController: NavController
) {
    
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(120.dp))

            WelcomeSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )

            Spacer(modifier = Modifier.height(80.dp))

            Text(
                text = stringResource(id = R.string.let_me_help_you),
                fontSize = 20.sp,
                fontFamily = Fredoka,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(id = R.string.in_order_to),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )

        }

        GradientButton(
            text = stringResource(id = R.string.lets_go),
            icon = null,
            modifier = Modifier
                .height(75.dp)
                .width(260.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = 25.dp)
        ){
            navController.navigate(NavigationRoute.GenderScreen.route)
        }
        
    }
    
}

@Preview
@Composable
fun IntroductionScreenPreview(){
    IntroductionScreen(
        navController = rememberNavController()
    )
}