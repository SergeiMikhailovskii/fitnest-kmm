package com.fitnest.domain.usecase.registration

import com.fitnest.domain.entity.RegistrationScreenState
import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.domain.extension.mapError
import com.fitnest.domain.mapper.RegistrationResponseMapper
import com.fitnest.domain.repository.NetworkRepository
import kotlinx.serialization.json.jsonObject

class GetRegistrationStepData(
    private val repository: NetworkRepository,
    private val registrationResponseMapper: RegistrationResponseMapper,
    private val registrationScreenState: RegistrationScreenState,
    private val exceptionHandler: ExceptionHandler
) {

    suspend operator fun invoke() = runCatching {
        repository.getRegistrationStepData()
    }.map {
        val data = it.data?.jsonObject
        registrationResponseMapper(data)
    }.onSuccess {
        registrationScreenState.fields = it.fields
        registrationScreenState.validationSchema = it.validationSchema
    }.mapError(exceptionHandler::getError)
}
