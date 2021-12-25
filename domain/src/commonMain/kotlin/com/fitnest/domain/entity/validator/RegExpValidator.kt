package com.fitnest.domain.entity.validator

import kotlinx.serialization.Serializable

@Serializable
class RegExpValidator(private val validation: String) : Validator() {

    override fun isValid(field: Any?) = when (field) {
        is String -> {
            Regex(validation).matches(field)
        }
        null -> {
            throw RuntimeException("RegExpValidator cannot isValid nullable field")
        }
        else -> {
            throw RuntimeException("RegExpValidator cannot isValid ${field::class.simpleName}")
        }
    }
}