package com.fitnest.android.screen.private_area.activity_tracker.data

import androidx.annotation.DrawableRes
import com.fitnest.android.screen.private_area.home.data.HomeScreenData
import com.fitnest.domain.enum.ActivityType
import kotlinx.collections.immutable.ImmutableList

internal data class ActivityTrackerScreenData(
    val activityProgressWidget: ActivityProgressWidget? = null,
    val latestActivityWidget: LatestActivityWidget? = null,
    val todayTargetWidget: HomeScreenData.TodayTargetWidget? = null,
) {

    class ActivityProgressWidget(
        val progresses: ImmutableList<Progress>? = null
    )

    class LatestActivityWidget(
        val activities: ImmutableList<Activity>? = null,
    )

    class Activity(
        val id: Int,
        val title: String,
        val description: String,
        val type: ActivityType,
        @DrawableRes val icon: Int
    )

    data class Progress(
        val day: String,
        val progress: Float,
        val color: Int? = null
    )


}