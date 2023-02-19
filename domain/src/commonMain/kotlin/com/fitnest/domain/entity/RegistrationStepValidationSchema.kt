package com.fitnest.domain.entity

import com.fitnest.domain.entity.validator.Validator
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class RegistrationStepValidationSchema {

    @Serializable
    class CreateAccountStepValidationSchema(
        @SerialName("first_name")
        val firstName: List<Validator>? = null,
        @SerialName("last_name")
        val lastName: List<Validator>? = null,
        val email: List<Validator>? = null,
        val password: List<Validator>? = null
    ) : RegistrationStepValidationSchema()

    @Serializable
    class CompleteAccountStepValidationSchema(
        val sex: List<Validator>? = null,
        @SerialName("date_of_birth")
        val dateOfBirth: List<Validator>? = null,
        val weight: List<Validator>? = null,
        val height: List<Validator>? = null
    ) : RegistrationStepValidationSchema()
}
