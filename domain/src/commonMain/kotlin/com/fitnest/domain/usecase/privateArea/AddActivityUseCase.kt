package com.fitnest.domain.usecase.privateArea

import com.fitnest.domain.entity.request.AddActivityRequest
import com.fitnest.domain.entity.response.ActivityTrackerPageResponse
import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.domain.extension.mapError
import com.fitnest.domain.mapper.db.ActivityTrackerResponseToCacheMapperAlias
import com.fitnest.domain.repository.DatabaseRepository
import com.fitnest.domain.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

class AddActivityUseCase(
    private val repository: NetworkRepository,
    private val dbRepository: DatabaseRepository,
    private val json: Json,
    private val responseToCacheMapper: ActivityTrackerResponseToCacheMapperAlias,
    private val exceptionHandler: ExceptionHandler
) {

    suspend operator fun invoke(request: AddActivityRequest) =
        runCatching {
            repository.addActivity(request)
            val response = repository.getActivityTrackerPage()
            val data =
                response.data?.let<JsonElement, ActivityTrackerPageResponse>(json::decodeFromJsonElement)
            val cacheModel = responseToCacheMapper.map(data)
            withContext(Dispatchers.Default) {
                dbRepository.saveActivityTrackerResponse(cacheModel)
            }
        }.mapError(exceptionHandler::getError)
}
