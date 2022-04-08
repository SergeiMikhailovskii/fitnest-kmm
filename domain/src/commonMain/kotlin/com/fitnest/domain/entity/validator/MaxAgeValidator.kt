package com.fitnest.domain.entity.validator

import kotlinx.datetime.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("maxAge")
class MaxAgeValidator(private val validation: Int) : Validator() {
    override fun isValid(field: Any?) = when (field) {
        is LocalDate -> {
            val years = field.yearsUntil(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date)
            years <= validation
        }
        else -> false
    }
}