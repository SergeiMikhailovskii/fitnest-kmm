package com.fitnest.domain.functional

import kotlinx.serialization.Serializable

sealed class Failure(message: String? = null) : Exception(message) {

    constructor() : this(null)

    data class ServerError(val responseCode: Int) : Failure()
    object Unknown : Failure()
    object OnboardingFinished : Failure()

    @Serializable
    data class ValidationError(
        val field: String?,
        override val message: String
    ) : Failure(message)

    @Serializable
    data class ValidationErrors(
        val fields: List<ValidationError>
    ) : Failure()
}
