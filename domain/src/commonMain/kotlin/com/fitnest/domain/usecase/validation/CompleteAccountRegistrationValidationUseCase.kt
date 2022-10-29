package com.fitnest.domain.usecase.validation

import com.fitnest.domain.entity.RegistrationStepValidationSchema
import com.fitnest.domain.entity.request.CompleteAccountStepRequest
import com.fitnest.domain.exception.CompleteAccountRegistrationScreenException

class CompleteAccountRegistrationValidationUseCase internal constructor() {

    operator fun invoke(
        fields: CompleteAccountStepRequest,
        validationSchema: RegistrationStepValidationSchema.CompleteAccountStepValidationSchema?
    ) = runCatching {
        val heightError = validationSchema?.height?.firstOrNull {
            !it.isValid("height", fields.height)
        }
        val dateOfBirthError = validationSchema?.dateOfBirth?.firstOrNull {
            !it.isValid("date_of_birth", fields.dateOfBirth)
        }
        val sexError = validationSchema?.sex?.firstOrNull {
            !it.isValid("sex", fields.sex)
        }
        val weightError = validationSchema?.weight?.firstOrNull {
            !it.isValid("weight", fields.weight)
        }

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
