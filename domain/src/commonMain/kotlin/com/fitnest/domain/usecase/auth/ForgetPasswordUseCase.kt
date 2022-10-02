package com.fitnest.domain.usecase.auth

import com.fitnest.domain.entity.request.ForgetPasswordRequest
import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.domain.extension.mapError
import com.fitnest.domain.repository.NetworkRepository

class ForgetPasswordUseCase(
    private val repository: NetworkRepository,
    private val exceptionHandler: ExceptionHandler
) {

    suspend operator fun invoke(request: ForgetPasswordRequest) = runCatching {
        repository.forgetPassword(request)
    }.mapError(exceptionHandler::getError)
}
