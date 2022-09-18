package com.fitnest.domain.entity

data class RegistrationScreenState(
    var fields: RegistrationStepModel? = null,
    var validationSchema: RegistrationStepValidationSchema? = null
)
