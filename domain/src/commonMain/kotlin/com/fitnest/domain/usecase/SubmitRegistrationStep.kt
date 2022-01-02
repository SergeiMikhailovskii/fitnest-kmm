package com.fitnest.domain.usecase

import com.fitnest.domain.entity.base.BaseRequest
import com.fitnest.domain.repository.NetworkRepository

class SubmitRegistrationStep(private val networkRepository: NetworkRepository) :
    UseCaseParamsUnit<BaseRequest>() {

    override suspend fun run(params: BaseRequest) = networkRepository.submitRegistrationStep(params)

}