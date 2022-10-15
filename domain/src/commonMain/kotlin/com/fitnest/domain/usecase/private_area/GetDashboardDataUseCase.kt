package com.fitnest.domain.usecase.private_area

import com.fitnest.domain.entity.cache.DashboardCacheModel
import com.fitnest.domain.entity.response.DashboardResponse
import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.domain.extension.mapError
import com.fitnest.domain.repository.DatabaseRepository
import com.fitnest.domain.repository.NetworkRepository
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

class GetDashboardDataUseCase(
    private val networkRepository: NetworkRepository,
    private val dbRepository: DatabaseRepository,
    private val json: Json,
    private val exceptionHandler: ExceptionHandler
) {

    suspend operator fun invoke() = runCatching {
        val jsonData = networkRepository.getDashboardData()
        jsonData.data?.let<JsonElement, DashboardResponse>(json::decodeFromJsonElement)
    }.onSuccess {
        val activityStatusWidgetStr =
            it?.widgets?.let { json.encodeToString(it.activityStatusWidget) }
        val bmiWidgetStr = it?.widgets?.let { json.encodeToString(it.bmiWidget) }
        val latestWorkoutWidgetStr =
            it?.widgets?.let { json.encodeToString(it.latestWorkoutWidget) }
        val todayTargetWidgetStr = it?.widgets?.let { json.encodeToString(it.todayTargetWidget) }
        val headerWidgetStr = it?.widgets?.let { json.encodeToString(it.headerWidget) }
        dbRepository.saveDashboardResponse(
            DashboardCacheModel(
                activityStatusWidget = activityStatusWidgetStr,
                bmiWidget = bmiWidgetStr,
                latestWorkoutWidget = latestWorkoutWidgetStr,
                todayTargetWidget = todayTargetWidgetStr,
                headerWidget = headerWidgetStr,
            )
        )
    }.mapError(exceptionHandler::getError)
}
