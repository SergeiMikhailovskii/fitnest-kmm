package com.fitnest.android.screen.private_area.activity_tracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
internal fun ActivityTrackerScreen() {
    Scaffold {
        Column {
            TodayTargetBlock()
        }
    }
}
