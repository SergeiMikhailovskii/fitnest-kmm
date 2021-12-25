package com.fitnest.domain.entity.validator

import kotlinx.serialization.Serializable

@Serializable
sealed class Validator {
    val type: String? = null
    val error: String? = null

    abstract fun isValid(field: Any?): Boolean
}
