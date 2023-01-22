package com.fitnest.domain.mapper.db

import com.fitnest.domain.entity.cache.ActivityTrackerCacheModel
import com.fitnest.domain.entity.response.ActivityTrackerPageResponse
import com.fitnest.domain.mapper.Mapper
import kotlinx.datetime.Clock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ActivityTrackerResponseToCacheMapper(
    private val json: Json
) : Mapper<ActivityTrackerPageResponse, ActivityTrackerCacheModel> {
    override fun map(source: ActivityTrackerPageResponse?): ActivityTrackerCacheModel {
        val activityProgressWidgetStr =
            source?.widgets?.activityProgressWidget?.let(json::encodeToString)
        val latestActivityWidgetStr =
            source?.widgets?.latestActivityWidget?.let(json::encodeToString)
        val todayTargetWidgetStr = source?.widgets?.todayTargetWidget?.let(json::encodeToString)

        return ActivityTrackerCacheModel(
            activityProgressWidget = activityProgressWidgetStr,
            latestActivityWidget = latestActivityWidgetStr,
            todayTargetWidget = todayTargetWidgetStr,
            timeAt = Clock.System.now().toEpochMilliseconds()
        )
    }
}
