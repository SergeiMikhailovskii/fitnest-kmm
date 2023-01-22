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
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

class GetDashboardDataUseCase(
    private val networkRepository: NetworkRepository,
    private val dbRepository: DatabaseRepository,
    private val json: Json,
    private val exceptionHandler: ExceptionHandler,
    private val responseToCacheMapper: DashboardResponseToCacheMapper,
    private val cacheToResponseMapper: DashboardCacheToResponseMapper
) {

    suspend operator fun invoke() = runCatching {
        dbRepository.getDashboard()
    }.flatMap {
        if (it != null) {
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
