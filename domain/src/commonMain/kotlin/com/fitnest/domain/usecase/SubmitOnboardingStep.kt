package com.fitnest.domain.usecase

import com.fitnest.domain.repository.NetworkRepository

class SubmitOnboardingStep(private val repository: NetworkRepository) : UseCaseUnit() {

    override suspend fun run() = repository.submitOnboardingStep()

}