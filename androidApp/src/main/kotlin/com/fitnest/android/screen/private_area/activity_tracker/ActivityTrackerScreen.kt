package com.fitnest.android.screen.private_area.activity_tracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.android.screen.private_area.activity_tracker.data.ActivityProgressSectionData
import com.fitnest.android.screen.private_area.activity_tracker.data.LatestActivityItemData
import com.fitnest.android.style.BrandColor
import com.fitnest.android.style.Padding
import com.fitnest.android.style.SecondaryColor

@Preview
@Composable
internal fun ActivityTrackerScreen() {
    Scaffold {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            TodayTargetBlock(
                modifier = Modifier.padding(horizontal = Padding.Padding30)
            )
            ActivityProgressBlock(
                modifier = Modifier.padding(
                    top = Padding.Padding30,
                    start = Padding.Padding30,
                    end = Padding.Padding30
                ),
                sections = arrayOf(
                    ActivityProgressSectionData(
                        dayName = "Mon",
                        progress = 0.1F,
                        color = BrandColor.toArgb()
                    ),
                    ActivityProgressSectionData(
                        dayName = "Tue",
                        progress = 0.2F,
                        color = SecondaryColor.toArgb()
                    ),
                    ActivityProgressSectionData(
                        dayName = "Wed",
                        progress = 0.3F,
                        color = BrandColor.toArgb()
                    ),
                    ActivityProgressSectionData(
                        dayName = "Thu",
                        progress = 0.4F,
                        color = SecondaryColor.toArgb()
                    ),
                    ActivityProgressSectionData(
                        dayName = "Fri",
                        progress = 0.5F,
                        color = BrandColor.toArgb()
                    ),
                    ActivityProgressSectionData(
                        dayName = "Sat",
                        progress = 0.6F,
                        color = SecondaryColor.toArgb()
                    ),
                    ActivityProgressSectionData(
                        dayName = "Sun",
                        progress = 0.7F,
                        color = BrandColor.toArgb()
                    )
                )
            )
            LatestActivityBlock(
                modifier = Modifier.padding(Padding.Padding30),
                activities = listOf(
                    LatestActivityItemData("Drinking 300ml Water", "About 3 minutes ago"),
                    LatestActivityItemData("Drinking 300ml Water", "About 3 minutes ago"),
                    LatestActivityItemData("Drinking 300ml Water", "About 3 minutes ago"),
                    LatestActivityItemData("Drinking 300ml Water", "About 3 minutes ago"),
                    LatestActivityItemData("Drinking 300ml Water", "About 3 minutes ago"),
                )
            )
        }
    }
}
