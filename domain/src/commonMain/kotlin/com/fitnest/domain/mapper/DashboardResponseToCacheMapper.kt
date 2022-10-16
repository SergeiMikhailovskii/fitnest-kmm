package com.fitnest.domain.mapper

import com.fitnest.domain.entity.cache.DashboardCacheModel
import com.fitnest.domain.entity.response.DashboardResponse
import kotlinx.datetime.Clock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DashboardResponseToCacheMapper(
    private val json: Json
) : Mapper<DashboardResponse, DashboardCacheModel> {
    override fun map(source: DashboardResponse?): DashboardCacheModel {
        val activityStatusWidgetStr =
            source?.widgets?.let { json.encodeToString(it.activityStatusWidget) }
        val bmiWidgetStr = source?.widgets?.let { json.encodeToString(it.bmiWidget) }
        val latestWorkoutWidgetStr =
            source?.widgets?.let { json.encodeToString(it.latestWorkoutWidget) }
        val todayTargetWidgetStr =
            source?.widgets?.let { json.encodeToString(it.todayTargetWidget) }
        val headerWidgetStr = source?.widgets?.let { json.encodeToString(it.headerWidget) }

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
