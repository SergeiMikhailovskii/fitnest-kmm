package com.fitnest.domain.entity.validator

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("enum")
class EnumValidator(private val validation: List<String>) : Validator() {
    override fun isValid(fieldName: String, field: Any?) = validation.contains(field)
}
