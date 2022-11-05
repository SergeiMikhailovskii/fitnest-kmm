package com.fitnest.domain.entity.validator

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.yearsUntil
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("minAge")
class MinAgeValidator(private val validation: Int) : Validator() {
    override fun isValid(fieldName: String, field: Any?): Boolean {
        super.field = fieldName
        return when (field) {
            is LocalDate -> {
                val years = field.yearsUntil(
                    Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
                )
                years >= validation
            }
            else -> false
        }
    }
}
