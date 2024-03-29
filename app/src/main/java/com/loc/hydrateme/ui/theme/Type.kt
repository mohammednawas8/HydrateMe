package com.loc.hydrateme.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.loc.hydrateme.R

val Roboto = FontFamily(
    Font(R.font.roboto_bold,FontWeight.Bold),
    Font(R.font.roboto_light,FontWeight.Light),
    Font(R.font.roboto_medium,FontWeight.Medium),
    Font(R.font.roboto_regular,FontWeight.Normal),
    Font(R.font.roboto_thin,FontWeight.Thin)
)

val OpenSans = FontFamily(
    Font(R.font.opensans_semibold,FontWeight.SemiBold)
)

val Poppins = FontFamily(
    Font(R.font.poppins_medium, FontWeight.Medium)
)

val Fredoka = FontFamily(
    Font(R.font.fredokaone_regular, FontWeight.Normal)
)



// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        color = Color(0xFF909090)
    ),
    h3 = TextStyle(
        color = White,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = Roboto
    ),

    h2 = TextStyle(
        color = Color(0xFF1BAEEE),
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp
    )


    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)