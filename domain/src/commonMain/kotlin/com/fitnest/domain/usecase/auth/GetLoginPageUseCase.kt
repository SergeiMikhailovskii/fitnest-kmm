package com.fitnest.domain.usecase.auth

import com.fitnest.domain.entity.response.LoginPageResponse
import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.domain.extension.mapError
import com.fitnest.domain.repository.NetworkRepository
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

class GetLoginPageUseCase(
    private val repository: NetworkRepository,
    private val json: Json,
    private val exceptionHandler: ExceptionHandler
) {

    suspend operator fun invoke() = runCatching {
        repository.getLoginPage()
    }.map {
        it.data?.let<JsonElement, LoginPageResponse>(json::decodeFromJsonElement)
    }.mapError(exceptionHandler::getError)
}
