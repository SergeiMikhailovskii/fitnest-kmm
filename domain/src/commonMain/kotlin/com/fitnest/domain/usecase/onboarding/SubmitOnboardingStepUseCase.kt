package com.fitnest.domain.usecase.onboarding

import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.domain.extension.mapError
import com.fitnest.domain.repository.NetworkRepository

class SubmitOnboardingStepUseCase(
    private val repository: NetworkRepository,
    private val exceptionHandler: ExceptionHandler
) {

    suspend operator fun invoke() = runCatching {
        repository.submitOnboardingStep()
    }.mapError(exceptionHandler::getError)
}
