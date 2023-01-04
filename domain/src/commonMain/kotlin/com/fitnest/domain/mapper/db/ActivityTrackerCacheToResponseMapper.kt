package com.fitnest.domain.mapper.db

import com.fitnest.domain.entity.cache.ActivityTrackerCacheModel
import com.fitnest.domain.entity.response.ActivityTrackerPageResponse
import com.fitnest.domain.entity.response.DashboardResponse
import com.fitnest.domain.mapper.Mapper
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ActivityTrackerCacheToResponseMapper(
    private val json: Json
) : Mapper<ActivityTrackerCacheModel, ActivityTrackerPageResponse> {
    override fun map(source: ActivityTrackerCacheModel?): ActivityTrackerPageResponse {
        val activityProgressWidget = source?.activityProgressWidget
            ?.let<String, ActivityTrackerPageResponse.ActivityProgressWidget>(json::decodeFromString)
        val latestActivityWidget = source?.latestActivityWidget
            ?.let<String, ActivityTrackerPageResponse.LatestActivityWidget>(json::decodeFromString)
        val todayTargetWidget = source?.todayTargetWidget
            ?.let<String, DashboardResponse.TodayTargetWidget>(json::decodeFromString)

        return ActivityTrackerPageResponse(
            widgets = ActivityTrackerPageResponse.ActivityTrackerWidgets(
                activityProgressWidget = activityProgressWidget,
                latestActivityWidget = latestActivityWidget,
                todayTargetWidget = todayTargetWidget
            )
        )
    }
}
