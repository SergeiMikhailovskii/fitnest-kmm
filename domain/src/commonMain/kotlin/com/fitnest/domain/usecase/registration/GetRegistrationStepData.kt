package com.fitnest.domain.usecase.registration

import com.fitnest.domain.entity.GetRegistrationResponseData
import com.fitnest.domain.repository.NetworkRepository
import com.fitnest.domain.usecase.UseCase

class GetRegistrationStepData(private val repository: NetworkRepository) :
    UseCase<GetRegistrationResponseData>() {

    override suspend fun run() = repository.getRegistrationStepData()

}