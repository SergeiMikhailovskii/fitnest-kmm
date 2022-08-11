package com.fitnest.android.screen.private_area.activity_tracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.android.R
import com.fitnest.android.style.BrandColor
import com.fitnest.android.style.Padding
import com.fitnest.android.style.PoppinsSemiBoldStyle16Black
import me.bytebeats.views.charts.bar.BarChart
import me.bytebeats.views.charts.bar.BarChartData

@Preview
@Composable
internal fun ActivityTrackerScreen() {
    Scaffold {
        Column {
            TodayTargetBlock(
                modifier = Modifier.padding(horizontal = Padding.Padding30)
            )
            Text(
                stringResource(id = R.string.private_area_activity_tracker_screen_activity_progress_title),
                modifier = Modifier.padding(top = Padding.Padding30, start = Padding.Padding30),
                style = PoppinsSemiBoldStyle16Black
            )
            BarChart(
                barChartData = BarChartData(
                    bars = listOf(
                        BarChartData.Bar(1F, BrandColor, ""),
                        BarChartData.Bar(2F, BrandColor, ""),
                        BarChartData.Bar(3F, BrandColor, ""),
                        BarChartData.Bar(4F, BrandColor, ""),
                        BarChartData.Bar(5F, BrandColor, ""),
                    )
                )
            )
        }
    }
}
