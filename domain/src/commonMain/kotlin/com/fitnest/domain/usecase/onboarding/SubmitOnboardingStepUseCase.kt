package com.fitnest.domain.usecase.onboarding

import com.fitnest.domain.repository.NetworkRepository

class SubmitOnboardingStepUseCase(private val repository: NetworkRepository) {

    suspend operator fun invoke() = runCatching {
        repository.submitOnboardingStep()
    }
}
