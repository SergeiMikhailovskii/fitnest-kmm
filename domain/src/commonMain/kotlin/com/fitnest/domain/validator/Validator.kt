package com.fitnest.domain.validator

import com.fitnest.domain.entity.validator.Validator

abstract class BaseValidator {

    fun mapValidationRulesWithField(fieldName: String?, validators: List<Validator>?) =
        validators?.map {
            it.field = fieldName
            it
        }

}