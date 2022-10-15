package com.fitnest.domain.usecase.private_area

import com.fitnest.domain.entity.response.DashboardResponse
import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.domain.extension.mapError
import com.fitnest.domain.mapper.DashboardResponseToCacheMapper
import com.fitnest.domain.repository.DatabaseRepository
import com.fitnest.domain.repository.NetworkRepository
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

class GetDashboardDataUseCase(
    private val networkRepository: NetworkRepository,
    private val dbRepository: DatabaseRepository,
    private val json: Json,
    private val exceptionHandler: ExceptionHandler,
    private val cacheMapper: DashboardResponseToCacheMapper
) {

    suspend operator fun invoke() = runCatching {
        val jsonData = networkRepository.getDashboardData()
        jsonData.data?.let<JsonElement, DashboardResponse>(json::decodeFromJsonElement)
    }.onSuccess {
        val cacheModel = cacheMapper.map(it)
        dbRepository.saveDashboardResponse(cacheModel)
    }.mapError(exceptionHandler::getError)
}
