package com.fitnest.android.screen.private_area.notification

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@ExperimentalFoundationApi
@Composable
internal fun NotificationsScreen() {
    LazyColumn {
        items(150) {
            Text(
                "Hello, $it", modifier = Modifier.animateItemPlacement(tween(500))
            )
        }
    }
}