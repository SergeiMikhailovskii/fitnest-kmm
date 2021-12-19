package com.fitnest.domain.entity.validator

import kotlinx.serialization.Serializable

@Serializable
class RequiredValidator : Validator() {
    override fun validate(field: Any?) = when (field) {
        null -> {
            false
        }
        is String -> {
            field.isBlank()
        }
        else -> {
            throw RuntimeException("MinLengthValidator cannot validate ${field::class.simpleName}")
        }
    }
}