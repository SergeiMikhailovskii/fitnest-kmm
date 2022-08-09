package com.fitnest.domain.usecase.onboarding

import com.fitnest.domain.repository.NetworkRepository
import com.fitnest.domain.usecase.UseCaseUnit

class SubmitOnboardingStep(private val repository: NetworkRepository) : UseCaseUnit() {

    override suspend fun run() = repository.submitOnboardingStep()
}
