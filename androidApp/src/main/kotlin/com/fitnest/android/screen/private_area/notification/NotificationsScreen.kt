package com.fitnest.android.screen.private_area.notification

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@ExperimentalFoundationApi
@Composable
internal fun NotificationsScreen() {
    Box {
        LazyColumn {
            items(150) {
                Text("Hello, $it")
                Divider()
            }
        }
    }
}