package com.fitnest.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun DividerWithChild(
    modifier: Modifier,
    child: @Composable () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .weight(1F)
                .height(1.dp)
                .background(MaterialTheme.colorScheme.onSurfaceVariant)
        )
        child()
        Box(
            modifier = Modifier
                .weight(1F)
                .height(1.dp)
                .background(MaterialTheme.colorScheme.onSurfaceVariant)
        )
    }
}
