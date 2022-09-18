package com.fitnest.domain.usecase.registration

import com.fitnest.domain.entity.RegistrationScreenState
import com.fitnest.domain.entity.base.BaseRequest
import com.fitnest.domain.extension.flatMap
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.mapper.RegistrationResponseMapper
import com.fitnest.domain.repository.NetworkRepository
import kotlinx.serialization.json.jsonObject

class SubmitRegistrationStepAndGetNextUseCase(
    private val networkRepository: NetworkRepository,
    private val registrationResponseMapper: RegistrationResponseMapper,
    private val registrationScreenState: RegistrationScreenState
) {

    suspend operator fun invoke(params: BaseRequest) = runCatching {
        networkRepository.submitRegistrationStep(params)
        networkRepository.getRegistrationStepData()
    }.flatMap {
        if (it.errors != null) {
            Result.failure(Failure.ValidationErrors(it.errors))
        } else {
            Result.success(it)
        }
    }.map {
        val data = it.data?.jsonObject
        registrationResponseMapper(data)
    }.onSuccess {
        registrationScreenState.fields = it.fields
        registrationScreenState.validationSchema = it.validationSchema
    }
}
