package com.fitnest.domain.entity.validator

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("minValue")
class MinValueValidator(private val validation: Int) : Validator() {
    override fun isValid(field: Any?) = when (field) {
        null -> {
            false
        }
        is Int -> {
            field > validation
        }
        else -> {
            throw RuntimeException("MinValueValidator cannot validate ${field::class.simpleName}")
        }
    }
}