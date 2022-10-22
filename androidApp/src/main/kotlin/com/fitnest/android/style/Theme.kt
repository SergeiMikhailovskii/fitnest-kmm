package com.fitnest.android.style

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun FitnestTheme(content: @Composable () -> Unit) {
    val colorScheme = if (isSystemInDarkTheme()) DarkColors.getColorScheme()
    else LightColors.getColorScheme()
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
