package com.fitnest.domain.entity.validator

import kotlinx.serialization.Serializable

@Serializable
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