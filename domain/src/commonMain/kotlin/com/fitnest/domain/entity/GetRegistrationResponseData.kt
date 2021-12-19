package com.fitnest.domain.entity

import com.fitnest.domain.entity.validator.Validator

data class GetRegistrationResponseData(
    val step: String? = null,
    val fields: RegistrationStepModel? = null,
    val validationSchema: Map<String, List<Validator>>? = null
)

