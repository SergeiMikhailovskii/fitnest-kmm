package com.fitnest.domain.usecase.registration

import com.fitnest.domain.entity.GetRegistrationResponseData
import com.fitnest.domain.entity.base.BaseRequest
import com.fitnest.domain.functional.flatMap
import com.fitnest.domain.repository.NetworkRepository
import com.fitnest.domain.usecase.UseCaseParams

class SubmitRegistrationStepAndGetNext(private val networkRepository: NetworkRepository) :
    UseCaseParams<GetRegistrationResponseData, BaseRequest>() {

    override suspend fun run(params: BaseRequest) =
        networkRepository.submitRegistrationStep(params)
            .flatMap { networkRepository.getRegistrationStepData() }

}