package com.fitnest.domain.mapper.db

import com.fitnest.domain.entity.cache.DashboardCacheModel
import com.fitnest.domain.entity.response.DashboardResponse
import com.fitnest.domain.mapper.Mapper
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal class DashboardCacheToResponseMapper(
    private val json: Json
) : Mapper<DashboardCacheModel, DashboardResponse> {
    override fun map(source: DashboardCacheModel?): DashboardResponse {
        val bmiWidget =
            source?.bmiWidget?.let<String, DashboardResponse.BMIWidget>(json::decodeFromString)
        val headerWidget =
            source?.headerWidget?.let<String, DashboardResponse.HeaderWidget>(json::decodeFromString)
        val activityStatusWidget =
            source?.activityStatusWidget?.let<String, DashboardResponse.ActivityStatusWidget>(json::decodeFromString)
        val todayTargetWidget =
            source?.todayTargetWidget?.let<String, DashboardResponse.TodayTargetWidget>(json::decodeFromString)
        val latestWorkoutWidget =
            source?.latestWorkoutWidget?.let<String, DashboardResponse.LatestWorkoutWidget>(json::decodeFromString)
        return DashboardResponse(
            widgets = DashboardResponse.DashboardWidgets(
                activityStatusWidget = activityStatusWidget,
                bmiWidget = bmiWidget,
                headerWidget = headerWidget,
                latestWorkoutWidget = latestWorkoutWidget,
                todayTargetWidget = todayTargetWidget
            )
        )
    }
}
