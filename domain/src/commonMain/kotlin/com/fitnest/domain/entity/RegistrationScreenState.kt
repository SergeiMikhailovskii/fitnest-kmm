package com.fitnest.domain.entity

import com.fitnest.domain.entity.validator.Validator

data class RegistrationScreenState(
    var fields: RegistrationStepModel? = null,
    var validationSchema: Map<String, List<Validator>>? = null
)