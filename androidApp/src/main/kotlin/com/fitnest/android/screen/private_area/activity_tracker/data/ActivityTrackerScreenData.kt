package com.fitnest.android.screen.private_area.activity_tracker.data

import com.fitnest.android.screen.private_area.home.data.HomeScreenData
import com.fitnest.domain.enum.ActivityType
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

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
        val amount: Int? = null,
        val time: LocalDateTime? = null,
        val type: ActivityType? = null
    )

    class Progress(
        val date: LocalDate? = null,
        val total: Int? = null
    )


}