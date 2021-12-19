package com.fitnest.android.screen.registration

import com.fitnest.domain.entity.RegistrationStepModel
import com.fitnest.domain.entity.validator.Validator

data class RegistrationScreenState(
    var fields: RegistrationStepModel? = null,
    var validationSchema: Map<String, List<Validator>>? = null
)