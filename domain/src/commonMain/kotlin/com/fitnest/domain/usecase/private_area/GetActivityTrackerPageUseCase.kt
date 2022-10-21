package com.fitnest.domain.usecase.private_area

import com.fitnest.domain.entity.response.ActivityTrackerPageResponse
import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.domain.extension.flatMap
import com.fitnest.domain.extension.isToday
import com.fitnest.domain.extension.mapError
import com.fitnest.domain.mapper.db.ActivityTrackerCacheToResponseMapper
import com.fitnest.domain.mapper.db.ActivityTrackerResponseToCacheMapper
import com.fitnest.domain.repository.DatabaseRepository
import com.fitnest.domain.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

class GetActivityTrackerPageUseCase internal constructor(
    private val networkRepository: NetworkRepository,
    private val dbRepository: DatabaseRepository,
    private val json: Json,
    private val exceptionHandler: ExceptionHandler,
    private val responseToCacheMapper: ActivityTrackerResponseToCacheMapper,
    private val cacheToResponseMapper: ActivityTrackerCacheToResponseMapper
) {

    suspend operator fun invoke() = runCatching {
        dbRepository.getActivityTracker()
    }.flatMap {
        val timeAt = it?.timeAt ?: 0
        if (it != null && timeAt.isToday) {
            Result.success(cacheToResponseMapper.map(it))
        } else {
            runCatching {
                val jsonData = networkRepository.getActivityTrackerPage()
                jsonData.data?.let<JsonElement, ActivityTrackerPageResponse>(json::decodeFromJsonElement)
            }.onSuccess {
                val cacheModel = responseToCacheMapper.map(it)
                withContext(Dispatchers.Default) {
                    dbRepository.saveActivityTrackerResponse(cacheModel)
                }
            }
        }
    }.map {
        it?.widgets
    }.mapError(exceptionHandler::getError)
}
