package com.fitnest.presentation.style

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

internal val ErrorStyle
    @Composable
    get() = MaterialTheme.typography.bodySmall.copy(
        color = MaterialTheme.colorScheme.error
    )

internal val BodyLargeOnBackground
    @Composable
    get() = MaterialTheme.typography.bodyLarge.copy(
        color = MaterialTheme.colorScheme.onBackground
    )

internal val TitleMediumOnBackground
    @Composable
    get() = MaterialTheme.typography.titleMedium.copy(
        color = MaterialTheme.colorScheme.onBackground
    )
