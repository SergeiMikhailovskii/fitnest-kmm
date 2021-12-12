package com.fitnest.domain.usecase

import com.fitnest.domain.entity.GetRegistrationResponseData
import com.fitnest.domain.repository.NetworkRepository

class GetRegistrationStepData(private val repository: NetworkRepository) :
    UseCase<GetRegistrationResponseData>() {

    override suspend fun run() = repository.getRegistrationStepData()

}