package com.fitnest.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class RegistrationStepModel {

    @Serializable
    data class CreateAccountStepModel(
        @SerialName("first_name")
        val firstName: String? = null,
        @SerialName("last_name")
        val lastName: String? = null,
        val email: String? = null,
        val password: String? = null
    ) : RegistrationStepModel()

    @Serializable
    data class WelcomeBackStepModel(
        val name: String? = null
    ) : RegistrationStepModel()
}
