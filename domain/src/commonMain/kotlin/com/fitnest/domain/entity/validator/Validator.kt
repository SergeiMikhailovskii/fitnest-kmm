package com.fitnest.domain.entity.validator

import kotlinx.serialization.Serializable

@Serializable
sealed class Validator {
    open val error: String? = null
    var field: String? = null

    abstract fun isValid(fieldName: String, field: Any?): Boolean
}
