package com.fitnest.domain.entity.validator

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("maxValue")
class MaxValueValidator(private val validation: Int) : Validator() {
    override fun isValid(fieldName: String, field: Any?): Boolean {
        super.field = fieldName
        return when (field) {
            null -> {
                false
            }
            is Int -> {
                field < validation
            }
            else -> {
                throw RuntimeException("MaxValueValidator cannot validate ${field::class.simpleName}")
            }
        }
    }
}
