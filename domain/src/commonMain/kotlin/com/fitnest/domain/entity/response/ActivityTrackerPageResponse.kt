package com.fitnest.domain.entity.response

import com.fitnest.domain.enum.ActivityType
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ActivityTrackerPageResponse(
    val widgets: ActivityTrackerWidgets? = null
) {

    @Serializable
    class ActivityTrackerWidgets(
        @SerialName("ACTIVITY_PROGRESS_WIDGET")
        val activityProgressWidget: ActivityProgressWidget? = null,
        @SerialName("LATEST_ACTIVITY_WIDGET")
        val latestActivityWidget: LatestActivityWidget? = null,
        @SerialName("TODAY_TARGET_WIDGET")
        val todayTargetWidget: DashboardResponse.TodayTargetWidget? = null
    )

    @Serializable
    class LatestActivityWidget(
        val activities: List<Activity>? = null
    )

    @Serializable
    class ActivityProgressWidget(
        val progresses: List<Progress>? = null
    )

    @Serializable
    class Activity(
        val id: Int? = null,
        val amount: Int? = null,
        val time: LocalDateTime? = null,
        val type: ActivityType? = null
    )

    @Serializable
    class Progress(
        val date: LocalDate? = null,
        val total: Int? = null
    )
}
