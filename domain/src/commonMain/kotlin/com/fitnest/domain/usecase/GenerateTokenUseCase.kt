package com.fitnest.domain.usecase

import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.domain.extension.mapError
import com.fitnest.domain.repository.NetworkRepository

class GenerateTokenUseCase(
    private val repository: NetworkRepository,
    private val exceptionHandler: ExceptionHandler
) {

    suspend operator fun invoke() = runCatching { repository.generateToken() }
        .mapError(exceptionHandler::getError)
}
