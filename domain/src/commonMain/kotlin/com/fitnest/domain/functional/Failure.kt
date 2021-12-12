package com.fitnest.domain.functional

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

sealed class Failure {
    data class ServerError(val responseCode: Int) : Failure()

    @Serializable
    data class ValidationError(
        val field: String?,
        val message: String
    ) : Failure()

    @Serializable
    data class ValidationErrors(
        val fields: List<ValidationError>
    ): Failure()
}
