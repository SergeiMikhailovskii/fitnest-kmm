package com.fitnest.domain.entity.validator

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("regExp")
class RegExpValidator(private val validation: String) : Validator() {

    override val error: String
        get() = "${super.field}.${super.error}"

    override fun isValid(field: Any?) = when (field) {
        is String -> {
            Regex(validation).matches(field)
        }
        null -> {
            throw RuntimeException("RegExpValidator cannot validate nullable field")
        }
        else -> {
            throw RuntimeException("RegExpValidator cannot validate ${field::class.simpleName}")
        }
    }
}