package com.fitnest.domain.usecase.onboarding

import com.fitnest.domain.entity.response.GetOnboardingStepResponse
import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.domain.extension.mapError
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.repository.NetworkRepository
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

class SubmitAndGetNextOnboardingStepUseCase(
    private val repository: NetworkRepository,
    private val json: Json,
    private val exceptionHandler: ExceptionHandler
) {

    suspend operator fun invoke() = runCatching {
        repository.submitOnboardingStep()
        val response = repository.getOnboardingStep()
        response.errors?.let {
            throw Failure.ValidationErrors(it)
        }
        val data = response.data?.let<JsonElement, GetOnboardingStepResponse>(json::decodeFromJsonElement)
        data?.step
    }.mapError(exceptionHandler::getError)
        .getOrThrow()
}
