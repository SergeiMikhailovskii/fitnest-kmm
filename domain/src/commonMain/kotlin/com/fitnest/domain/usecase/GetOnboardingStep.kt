package com.fitnest.domain.usecase

import com.fitnest.domain.repository.NetworkRepository

class GetOnboardingStep(private val repository: NetworkRepository) : UseCase<String>() {

    override suspend fun run() = repository.getOnboardingStep()

}