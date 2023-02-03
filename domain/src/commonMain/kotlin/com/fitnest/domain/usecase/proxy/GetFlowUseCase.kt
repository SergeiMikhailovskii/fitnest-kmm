package com.fitnest.domain.usecase.proxy

import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.domain.extension.mapError
import com.fitnest.domain.repository.NetworkRepository

class GetFlowUseCase(
    private val repository: NetworkRepository,
    private val exceptionHandler: ExceptionHandler
) {

    suspend operator fun invoke() = runCatching {
        val response = repository.generateToken()
        response.getFlow()
    }.mapError(exceptionHandler::getError)
}
