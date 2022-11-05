package com.fitnest.domain.entity.validator

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("minLength")
class MinLengthValidator(private val validation: Int) : Validator() {

    override val error: String
        get() = "${super.field}.${super.error}"

    override fun isValid(fieldName: String, field: Any?): Boolean {
        super.field = fieldName
        return when (field) {
            is String -> {
                field.length >= validation
            }
            null -> {
                throw RuntimeException("MinLengthValidator cannot validate nullable field")
            }
            else -> {
                throw RuntimeException("MinLengthValidator cannot validate ${field::class.simpleName}")
            }
        }
    }
}
