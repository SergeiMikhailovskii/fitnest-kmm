package com.fitnest.android.screen.private_area.activity_tracker.data

import androidx.annotation.DrawableRes
import com.fitnest.android.screen.private_area.home.data.HomeScreenData
import com.fitnest.domain.enum.ActivityType

internal data class ActivityTrackerScreenData(
    val activityProgressWidget: ActivityProgressWidget? = null,
    val latestActivityWidget: LatestActivityWidget? = null,
    val todayTargetWidget: HomeScreenData.TodayTargetWidget? = null,
) {

    class ActivityProgressWidget(
        val progresses: List<Progress>? = null
    )

    class LatestActivityWidget(
        val activities: List<Activity>? = null,
    )

    class Activity(
        val id: Int,
        val title: String,
        val description: String,
        val type: ActivityType,
        @DrawableRes val icon: Int
    )

    class Progress(
        val day: String,
        val progress: Float,
        val color: Int
    )


}