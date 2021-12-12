package com.fitnest.domain.entity

import kotlinx.serialization.Serializable

@Serializable
sealed class RegistrationStepModel {

    @Serializable
    data class CreateAccountStepModel(
        val firstName: String? = null,
        val lastName: String? = null,
        val email: String? = null,
        val password: String? = null
    ) : RegistrationStepModel()
}
