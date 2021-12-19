package com.fitnest.domain.entity.validator

import kotlinx.serialization.Serializable

@Serializable
class MinLengthValidator(private val validation: Int) : Validator() {

    override fun validate(field: Any?) = when (field) {
        is String -> {
            field.length < validation
        }
        null -> {
            throw RuntimeException("MinLengthValidator cannot validate nullable field")
        }
        else -> {
            throw RuntimeException("MinLengthValidator cannot validate ${field::class.simpleName}")
        }
    }
}