package com.fitnest.presentation.extension

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val ColorScheme.tertiaryGradient: List<Color>
    @Composable
    get() = listOf(tertiary, tertiaryContainer)

val ColorScheme.brandGradient: List<Color>
    @Composable
    get() = listOf(surfaceTint, primary)
