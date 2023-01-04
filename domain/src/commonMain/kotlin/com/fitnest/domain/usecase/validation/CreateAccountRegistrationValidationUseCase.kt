package com.fitnest.domain.usecase.validation

import com.fitnest.domain.entity.RegistrationStepValidationSchema
import com.fitnest.domain.entity.request.CreateAccountStepRequest
import com.fitnest.domain.exception.CreateAccountRegistrationScreenException

class CreateAccountRegistrationValidationUseCase {

    operator fun invoke(
        fields: CreateAccountStepRequest,
        validationSchema: RegistrationStepValidationSchema.CreateAccountStepValidationSchema?
    ) = runCatching {
        val emailError = validationSchema?.email?.firstOrNull {
            !it.isValid("email", fields.email)
        }
        val firstNameError = validationSchema?.firstName?.firstOrNull {
            !it.isValid("first_name", fields.firstName)
        }
        val lastNameError = validationSchema?.lastName?.firstOrNull {
            !it.isValid("last_name", fields.lastName)
        }
        val passwordError = validationSchema?.password?.firstOrNull {
            !it.isValid("password", fields.password)
        }

        if (emailError != null || firstNameError != null || lastNameError != null || passwordError != null) {
            throw CreateAccountRegistrationScreenException(
                emailError = emailError?.error,
                firstNameError = firstNameError?.error,
                lastNameError = lastNameError?.error,
                passwordError = passwordError?.error,
            )
        }
    }
}
