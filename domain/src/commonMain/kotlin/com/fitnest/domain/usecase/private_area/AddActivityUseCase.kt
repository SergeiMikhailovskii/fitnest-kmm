package com.fitnest.domain.usecase.private_area

import com.fitnest.domain.entity.request.AddActivityRequest
import com.fitnest.domain.entity.response.ActivityTrackerPageResponse
import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.domain.extension.mapError
import com.fitnest.domain.mapper.db.ActivityTrackerResponseToCacheMapper
import com.fitnest.domain.repository.DatabaseRepository
import com.fitnest.domain.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

class AddActivityUseCase internal constructor(
    private val repository: NetworkRepository,
    private val dbRepository: DatabaseRepository,
    private val json: Json,
    private val responseToCacheMapper: ActivityTrackerResponseToCacheMapper,
    private val exceptionHandler: ExceptionHandler
) {

    suspend operator fun invoke(request: AddActivityRequest) =
        runCatching {
            repository.addActivity(request)
            repository.getActivityTrackerPage()
        }.map {
            it.data?.let<JsonElement, ActivityTrackerPageResponse>(json::decodeFromJsonElement)
        }.onSuccess {
            val cacheModel = responseToCacheMapper.map(it)
            withContext(Dispatchers.Default) {
                dbRepository.saveActivityTrackerResponse(cacheModel)
            }
        }.map {
            it?.widgets
        }.mapError(exceptionHandler::getError)
}
