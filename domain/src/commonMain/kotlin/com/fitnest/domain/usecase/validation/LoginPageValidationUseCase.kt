package com.fitnest.domain.usecase.validation

import com.fitnest.domain.entity.response.LoginPageResponse
import com.fitnest.domain.exception.LoginPageValidationException

class LoginPageValidationUseCase {

    operator fun invoke(
        fields: LoginPageResponse.LoginPageFields,
        validationSchema: LoginPageResponse.LoginPageValidationSchema?
    ) = runCatching<Unit> {
        val loginError = validationSchema?.login?.firstOrNull { !it.isValid(fields.login) }
        val passwordError = validationSchema?.login?.firstOrNull { !it.isValid(fields.login) }

        if (loginError != null || passwordError != null) {
            throw LoginPageValidationException(
                loginError = loginError?.error,
                passwordError = passwordError?.error
            )
        }
    }
}
