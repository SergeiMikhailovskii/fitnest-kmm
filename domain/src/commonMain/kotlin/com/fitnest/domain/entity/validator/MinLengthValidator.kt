package com.fitnest.domain.entity.validator

import kotlinx.serialization.Serializable

@Serializable
class MinLengthValidator : Validator() {
    val validation: Int? = null
}