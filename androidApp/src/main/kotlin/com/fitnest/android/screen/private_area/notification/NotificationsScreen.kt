package com.fitnest.android.screen.private_area.notification

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fitnest.android.style.Padding

@ExperimentalFoundationApi
@Composable
internal fun NotificationsScreen() {
    Box {
        LazyColumn(modifier = Modifier.padding(horizontal = Padding.Padding16)) {
            items(150) {
                NotificationItem()
                Divider()
            }
        }
    }
}