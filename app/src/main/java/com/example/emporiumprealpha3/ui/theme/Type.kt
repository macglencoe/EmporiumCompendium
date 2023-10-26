package com.example.emporiumprealpha3.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import com.example.emporiumprealpha3.R

object AppFont {
    val Poppins = FontFamily(
        Font(R.font.poppins_regular),
        Font(R.font.poppins_italic, style = FontStyle.Italic),
        Font(R.font.poppins_semibold, FontWeight.SemiBold),
        Font(R.font.poppins_semibolditalic, FontWeight.SemiBold, style = FontStyle.Italic),
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_bolditalic, FontWeight.Bold, style = FontStyle.Italic)
    )
}

private val defaultTypography = Typography()
val typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = AppFont.Poppins),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = AppFont.Poppins),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = AppFont.Poppins),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = AppFont.Poppins),
    headlineMedium = defaultTypography.headlineMedium.copy(
        fontFamily = AppFont.Poppins,
        fontSize = 24.sp
    ),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = AppFont.Poppins),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = AppFont.Poppins),
    titleMedium = defaultTypography.titleMedium.copy(
        fontFamily = AppFont.Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = AppFont.Poppins),

    bodyLarge = defaultTypography.bodyLarge.copy(
        fontFamily = AppFont.Poppins,
        fontSize = 16.sp
    ),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = AppFont.Poppins),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = AppFont.Poppins),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = AppFont.Poppins),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = AppFont.Poppins),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = AppFont.Poppins)

)