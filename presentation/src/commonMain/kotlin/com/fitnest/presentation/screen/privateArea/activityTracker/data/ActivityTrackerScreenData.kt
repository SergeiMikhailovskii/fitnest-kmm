package com.fitnest.presentation.screen.privateArea.activityTracker.data

import com.fitnest.domain.enum.ActivityType
import com.fitnest.presentation.screen.privateArea.home.data.HomeScreenData
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.collections.immutable.ImmutableList

data class ActivityTrackerScreenData(
    val activityProgressWidget: ActivityProgressWidget? = ActivityProgressWidget(),
    val latestActivityWidget: LatestActivityWidget? = null,
    val todayTargetWidget: HomeScreenData.TodayTargetWidget? = HomeScreenData.TodayTargetWidget()
) {

    data class ActivityProgressWidget(
        val progresses: ImmutableList<Progress>? = null
    )

    data class LatestActivityWidget(
        val activities: ImmutableList<Activity>? = null
    )

    data class Activity(
        val id: Int,
        val title: StringDesc,
        val description: String,
        val type: ActivityType,
        val icon: String
    )

    data class Progress(
        val day: StringResource,
        val progress: Float,
        val color: Int? = null
    )
}
