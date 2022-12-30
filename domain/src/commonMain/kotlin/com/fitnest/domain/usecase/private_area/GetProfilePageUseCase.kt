package com.fitnest.domain.usecase.private_area

import com.fitnest.domain.entity.response.ProfilePageResponse
import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.domain.extension.mapError
import com.fitnest.domain.repository.NetworkRepository
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

class GetProfilePageUseCase internal constructor(
    private val networkRepository: NetworkRepository,
    private val json: Json,
    private val exceptionHandler: ExceptionHandler
) {

    suspend operator fun invoke() = runCatching {
        networkRepository.getProfilePage()
    }.map {
        val decoded =
            it.data?.let<JsonElement, ProfilePageResponse>(json::decodeFromJsonElement)
        decoded?.widgets?.profileInfoWidget
    }.mapError(exceptionHandler::getError)
}
