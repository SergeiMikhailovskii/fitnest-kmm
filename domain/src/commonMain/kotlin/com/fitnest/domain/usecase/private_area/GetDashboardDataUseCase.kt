package com.fitnest.domain.usecase.private_area

import com.fitnest.domain.entity.response.DashboardResponse
import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.domain.extension.flatMap
import com.fitnest.domain.extension.mapError
import com.fitnest.domain.mapper.db.DashboardCacheToResponseMapper
import com.fitnest.domain.mapper.db.DashboardResponseToCacheMapper
import com.fitnest.domain.repository.DatabaseRepository
import com.fitnest.domain.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.todayIn
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

class GetDashboardDataUseCase internal constructor(
    private val networkRepository: NetworkRepository,
    private val dbRepository: DatabaseRepository,
    private val json: Json,
    private val exceptionHandler: ExceptionHandler,
    private val responseToCacheMapper: DashboardResponseToCacheMapper,
    private val cacheToResponseMapper: DashboardCacheToResponseMapper
) {

    suspend operator fun invoke() =
        runCatching {
            dbRepository.getDashboard()
        }.flatMap {
            val timeAt = it?.timeAt ?: 0
            val dayStart = LocalDateTime(
                Clock.System.todayIn(TimeZone.currentSystemDefault()),
                LocalTime(0, 0)
            ).toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
            val dayEnd = LocalDateTime(
                Clock.System.todayIn(TimeZone.currentSystemDefault()).plus(1, DateTimeUnit.DAY),
                LocalTime(0, 0)
            ).toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()

            if (it != null && timeAt in dayStart until dayEnd) {
                Result.success(cacheToResponseMapper.map(it))
            } else {
                runCatching {
                    val jsonData = networkRepository.getDashboardData()
                    jsonData.data?.let<JsonElement, DashboardResponse>(json::decodeFromJsonElement)
                }.onSuccess {
                    val cacheModel = responseToCacheMapper.map(it)
                    withContext(Dispatchers.Default) { dbRepository.saveDashboardResponse(cacheModel) }
                }
            }
        }.mapError(exceptionHandler::getError)
}
