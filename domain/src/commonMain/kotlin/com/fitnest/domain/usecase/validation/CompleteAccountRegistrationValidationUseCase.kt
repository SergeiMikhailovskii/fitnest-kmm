package com.fitnest.domain.usecase.validation

import com.fitnest.domain.entity.RegistrationStepValidationSchema
import com.fitnest.domain.entity.request.CompleteAccountStepRequest
import com.fitnest.domain.exception.CompleteAccountRegistrationScreenException

class CompleteAccountRegistrationValidationUseCase {

    operator fun invoke(
        fields: CompleteAccountStepRequest,
        validationSchema: RegistrationStepValidationSchema.CompleteAccountStepValidationSchema?
    ) = runCatching {
        val heightError = validationSchema?.height?.firstOrNull { !it.isValid(fields.height) }
        val dateOfBirthError =
            validationSchema?.dateOfBirth?.firstOrNull { !it.isValid(fields.dateOfBirth) }
        val sexError = validationSchema?.sex?.firstOrNull { !it.isValid(fields.sex) }
        val weightError = validationSchema?.weight?.firstOrNull { !it.isValid(fields.weight) }

        if (heightError != null || dateOfBirthError != null || sexError != null || weightError != null) {
            throw CompleteAccountRegistrationScreenException(
                heightError = heightError?.error,
                genderError = sexError?.error,
                birthDateError = dateOfBirthError?.error,
                weightError = weightError?.error,
            )
        }
    }
}
