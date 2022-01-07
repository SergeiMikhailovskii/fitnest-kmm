package com.fitnest.domain.entity.validator

import kotlinx.serialization.Serializable

@Serializable
class MaxAgeValidator(private val validation: Int) : Validator() {
    override fun isValid(field: Any?): Boolean {
        // TODO: 7.01.22 Add validation logic
        return true
    }
}