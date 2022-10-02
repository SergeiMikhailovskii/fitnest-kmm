package com.fitnest.domain.usecase.auth

import com.fitnest.domain.entity.response.LoginPageResponse
import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.domain.exception.LoginPageValidationException
import com.fitnest.domain.extension.mapError
import com.fitnest.domain.repository.NetworkRepository

class LoginUserUseCase(
    private val repository: NetworkRepository,
    private val exceptionHandler: ExceptionHandler
) {

    suspend operator fun invoke(request: LoginPageResponse.LoginPageFields) = runCatching {
        repository.loginUser(request)
    }.map {
        val errors = it.errors
        if (!errors.isNullOrEmpty()) {
            throw LoginPageValidationException(
                loginError = errors.firstOrNull { it.field == "login" }?.message?.let { "login.$it" },
                passwordError = errors.firstOrNull { it.field == "password" }?.message?.let { "password.$it" }
            )
        }
    }.mapError(exceptionHandler::getError)
}
