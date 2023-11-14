package com.fitnest.presentation.style

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.fitnest.presentation.MR
import dev.icerock.moko.resources.compose.asFont
import dev.icerock.moko.resources.compose.fontFamilyResource

private val poppinsFamily: FontFamily
    @Composable get() = FontFamily(
        listOf(
            MR.fonts.Poppins.bold.asFont()!!,
            MR.fonts.Poppins.medium.asFont()!!,
            MR.fonts.Poppins.regular.asFont()!!,
            MR.fonts.Poppins.semibold.asFont()!!
        )
    )

@Composable
fun getTypography() = Typography(
    headlineSmall = TextStyle(
        fontFamily = poppinsFamily,
        fontSize = 24.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.Bold
    ),
    titleMedium = TextStyle(
        fontFamily = poppinsFamily,
        fontSize = 20.sp,
        lineHeight = 30.sp,
        fontWeight = FontWeight.Bold
    ),
    titleSmall = TextStyle(
        fontFamily = poppinsFamily,
        fontSize = 18.sp,
        lineHeight = 27.sp,
        fontWeight = FontWeight.Normal
    ),
    bodyLarge = TextStyle(
        fontFamily = poppinsFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Normal
    ),
    bodyMedium = TextStyle(
        fontFamily = fontFamilyResource(MR.fonts.Poppins.regular),
        fontSize = 14.sp,
        lineHeight = 21.sp,
        fontWeight = FontWeight.Normal
    ),
    bodySmall = TextStyle(
        fontFamily = poppinsFamily,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Normal
    ),
    labelLarge = TextStyle(
        fontFamily = poppinsFamily,
        fontSize = 10.sp,
        lineHeight = 15.sp,
        fontWeight = FontWeight.Normal
    ),
    labelSmall = TextStyle(
        fontFamily = poppinsFamily,
        fontSize = 8.sp,
        lineHeight = 12.sp,
        fontWeight = FontWeight.Normal
    )
)
