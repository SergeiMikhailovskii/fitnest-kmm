package com.mikhailovskii.kmmtest.android.view.base

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Composable
fun FitnestTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        content = content
    )
}

@Stable
class FitnestColors(
    brandGradient: List<Color> = listOf(Gradient1, Gradient2)
)
