package com.fitnest.exception

import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.domain.functional.Failure
import io.ktor.client.plugins.ResponseException

class GeneralExceptionHandler : ExceptionHandler {
    override fun getError(throwable: Throwable) = when (throwable) {
        is ResponseException -> Failure.ServerError(throwable.response.status.value)
        is Failure.ValidationErrors -> {
            if (throwable.fields.any { it.message == "onboarding.finished" }) Failure.OnboardingFinished
            else throwable
        }
        else -> Failure.Unknown
    }
}
