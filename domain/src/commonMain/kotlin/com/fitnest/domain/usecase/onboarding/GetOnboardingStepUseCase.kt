package com.fitnest.domain.usecase.onboarding

import com.fitnest.domain.entity.response.GetOnboardingStepResponse
import com.fitnest.domain.extension.flatMap
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.repository.NetworkRepository
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

class GetOnboardingStepUseCase(
    private val repository: NetworkRepository,
    private val json: Json
) {

    suspend operator fun invoke() = runCatching {
        repository.getOnboardingStep()
    }.flatMap {
        if (it.errors != null) {
            Result.failure(Failure.ValidationErrors(it.errors))
        } else {
            Result.success(it)
        }
    }.map {
        val data = it.data?.let<JsonElement, GetOnboardingStepResponse>(json::decodeFromJsonElement)
        data?.step
    }
}
