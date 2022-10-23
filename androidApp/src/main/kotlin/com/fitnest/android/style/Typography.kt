package com.fitnest.android.style

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

internal object Typography {
    fun getTypography() = Typography(
        headlineSmall = TextStyle(
            fontFamily = poppinsFamily,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 36.sp,
        ),
        titleMedium = TextStyle(
            fontFamily = poppinsFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 30.sp,
        ),
        titleSmall = TextStyle(
            fontFamily = poppinsFamily,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 27.sp,
        ),
        bodyLarge = TextStyle(
            fontFamily = poppinsFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 24.sp,
        ),
        bodyMedium = TextStyle(
            fontFamily = poppinsFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 21.sp,
        ),
        bodySmall = TextStyle(
            fontFamily = poppinsFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 18.sp,
        ),
        labelLarge = TextStyle(
            fontFamily = poppinsFamily,
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 15.sp,
        ),
        labelSmall = TextStyle(
            fontFamily = poppinsFamily,
            fontSize = 8.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 12.sp,
        )
    )
}
