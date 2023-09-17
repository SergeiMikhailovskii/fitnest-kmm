package com.fitnest.presentation.style

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.fitnest.presentation.MR
import dev.icerock.moko.resources.compose.fontFamilyResource

internal object Typography {
    @Composable
    fun getTypography() = Typography(
        headlineSmall = TextStyle(
            fontFamily = fontFamilyResource(MR.fonts.Poppins.bold),
            fontSize = 24.sp,
            lineHeight = 36.sp
        ),
        titleMedium = TextStyle(
            fontFamily = fontFamilyResource(MR.fonts.Poppins.bold),
            fontSize = 20.sp,
            lineHeight = 30.sp
        ),
        titleSmall = TextStyle(
            fontFamily = fontFamilyResource(MR.fonts.Poppins.regular),
            fontSize = 18.sp,
            lineHeight = 27.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = fontFamilyResource(MR.fonts.Poppins.regular),
            fontSize = 16.sp,
            lineHeight = 24.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = fontFamilyResource(MR.fonts.Poppins.regular),
            fontSize = 14.sp,
            lineHeight = 21.sp
        ),
        bodySmall = TextStyle(
            fontFamily = fontFamilyResource(MR.fonts.Poppins.regular),
            fontSize = 12.sp,
            lineHeight = 18.sp
        ),
        labelLarge = TextStyle(
            fontFamily = fontFamilyResource(MR.fonts.Poppins.regular),
            fontSize = 10.sp,
            lineHeight = 15.sp
        ),
        labelSmall = TextStyle(
            fontFamily = fontFamilyResource(MR.fonts.Poppins.regular),
            fontSize = 8.sp,
            lineHeight = 12.sp
        )
    )
}
