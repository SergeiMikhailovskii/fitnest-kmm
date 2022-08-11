package com.fitnest.android.screen.private_area.activity_tracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.android.style.Padding

@Preview
@Composable
internal fun ActivityTrackerScreen() {
    Scaffold {
        Column {
            TodayTargetBlock(
                modifier = Modifier.padding(horizontal = Padding.Padding30)
            )
        }
    }
}
