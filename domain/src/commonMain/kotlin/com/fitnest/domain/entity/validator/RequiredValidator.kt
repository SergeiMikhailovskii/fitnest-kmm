package com.fitnest.domain.entity.validator

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("required")
class RequiredValidator : Validator() {
    override fun isValid(fieldName: String, field: Any?) = when (field) {
        null -> {
            false
        }
        is String -> {
            field.isNotBlank()
        }
        else -> true
    }
}
