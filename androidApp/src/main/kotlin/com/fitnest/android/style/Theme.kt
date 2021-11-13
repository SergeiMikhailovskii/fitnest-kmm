package com.fitnest.android.style

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun FitnestTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = Colors(
            primary = BrandColor,
            primaryVariant = BrandColor1,
            secondary = SecondaryColor,
            secondaryVariant = SecondaryColor1,
            background = WhiteColor,
            surface = WhiteColor,
            error = ErrorColor,
            onPrimary = WhiteColor,
            onSecondary = BlackColor,
            onBackground = BlackColor,
            onSurface = BlackColor,
            onError = WhiteColor,
            isLight = darkTheme
        ),
        content = content
    )
}
