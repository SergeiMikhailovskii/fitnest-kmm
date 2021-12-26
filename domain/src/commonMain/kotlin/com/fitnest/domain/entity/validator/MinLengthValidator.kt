package com.fitnest.domain.entity.validator

import kotlinx.serialization.Serializable

@Serializable
class MinLengthValidator(private val validation: Int) : Validator() {

    override val error: String
        get() = "${super.field}.${super.error}"

    override fun isValid(field: Any?) = when (field) {
        is String -> {
            field.length < validation
        }
        null -> {
            throw RuntimeException("MinLengthValidator cannot isValid nullable field")
        }
        else -> {
            throw RuntimeException("MinLengthValidator cannot isValid ${field::class.simpleName}")
        }
    }
}