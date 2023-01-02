package com.fitnest.domain.usecase.private_area

import com.fitnest.domain.entity.response.ProfilePageResponse
import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.domain.extension.flatMap
import com.fitnest.domain.extension.mapError
import com.fitnest.domain.mapper.db.ProfileCacheToResponseMapper
import com.fitnest.domain.mapper.db.ProfileResponseToCacheMapper
import com.fitnest.domain.repository.DataStoreRepository
import com.fitnest.domain.repository.DatabaseRepository
import com.fitnest.domain.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

class GetProfilePageUseCase(
    private val networkRepository: NetworkRepository,
    private val dbRepository: DatabaseRepository,
    private val dataStoreRepository: DataStoreRepository,
    private val json: Json,
    private val exceptionHandler: ExceptionHandler,
    private val responseToCacheMapper: ProfileResponseToCacheMapper,
    private val cacheToResponseMapper: ProfileCacheToResponseMapper
) {

    suspend operator fun invoke() = runCatching {
        withContext(Dispatchers.Default) { dbRepository.getProfile() }
    }.flatMap {
        if (it != null) {
            Result.success(cacheToResponseMapper.map(it).widgets?.profileInfoWidget)
        } else {
            runCatching {
                networkRepository.getProfilePage()
            }.map {
                val decoded = it.data?.let<JsonElement, ProfilePageResponse>(
                    json::decodeFromJsonElement
                )
                val cacheModel = responseToCacheMapper.map(decoded)
                withContext(Dispatchers.Default) { dbRepository.saveProfileResponse(cacheModel) }
                decoded?.widgets?.profileInfoWidget
            }
        }
    }.flatMap {
        runCatching {
            val areNotificationsEnabled = dataStoreRepository.getNotificationsEnabled()
            Model(it, areNotificationsEnabled)
        }
    }.mapError(exceptionHandler::getError)

    class Model(
        val widget: ProfilePageResponse.ProfileInfoWidget?,
        val areNotificationsEnabled: Boolean
    )
}
