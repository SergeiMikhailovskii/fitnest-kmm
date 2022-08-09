package com.fitnest.domain.usecase.onboarding

import com.fitnest.domain.repository.NetworkRepository
import com.fitnest.domain.usecase.UseCase

class GetOnboardingStep(private val repository: NetworkRepository) : UseCase<String>() {

    override suspend fun run() = repository.getOnboardingStep()
}
