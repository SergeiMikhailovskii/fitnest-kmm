package com.fitnest.domain.mapper.db

import com.fitnest.domain.entity.cache.DashboardCacheModel
import com.fitnest.domain.entity.response.DashboardResponse
import com.fitnest.domain.mapper.Mapper
import kotlinx.datetime.Clock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DashboardResponseToCacheMapper(
    private val json: Json
) : Mapper<DashboardResponse, DashboardCacheModel> {
    override fun map(source: DashboardResponse?): DashboardCacheModel {
        val activityStatusWidgetStr =
            source?.widgets?.activityStatusWidget?.let(json::encodeToString)
        val bmiWidgetStr = source?.widgets?.bmiWidget?.let(json::encodeToString)
        val latestWorkoutWidgetStr = source?.widgets?.latestWorkoutWidget?.let(json::encodeToString)
        val todayTargetWidgetStr = source?.widgets?.todayTargetWidget?.let(json::encodeToString)
        val headerWidgetStr = source?.widgets?.headerWidget?.let(json::encodeToString)

        return DashboardCacheModel(
            activityStatusWidget = activityStatusWidgetStr,
            bmiWidget = bmiWidgetStr,
            latestWorkoutWidget = latestWorkoutWidgetStr,
            todayTargetWidget = todayTargetWidgetStr,
            headerWidget = headerWidgetStr,
            timeAt = Clock.System.now().toEpochMilliseconds()
        )
    }
}
