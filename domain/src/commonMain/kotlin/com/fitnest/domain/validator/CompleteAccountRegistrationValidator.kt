package com.fitnest.domain.validator

import com.fitnest.domain.entity.request.CompleteAccountStepRequest
import com.fitnest.domain.entity.validator.Validator

class CompleteAccountRegistrationValidator : BaseValidator() {

    private var onGenderErrorChanged: ((String) -> Unit)? = null
    private var onBirthDateErrorChanged: ((String) -> Unit)? = null
    private var onWeightErrorChanged: ((String) -> Unit)? = null
    private var onHeightErrorChanged: ((String) -> Unit)? = null

    private var genderValidators: List<Validator>? = null
    private var birthDateValidators: List<Validator>? = null
    private var weightValidators: List<Validator>? = null
    private var heightValidators: List<Validator>? = null

    fun initialize(
        jsonSchema: Map<String, List<Validator>>,
        onGenderErrorChanged: (String) -> Unit,
        onBirthDateErrorChanged: (String) -> Unit,
        onWeightErrorChanged: (String) -> Unit,
        onHeightErrorChanged: (String) -> Unit,
    ) {
        this.onGenderErrorChanged = onGenderErrorChanged
        this.onBirthDateErrorChanged = onBirthDateErrorChanged
        this.onWeightErrorChanged = onWeightErrorChanged
        this.onHeightErrorChanged = onHeightErrorChanged

        this.genderValidators =
            mapValidationRulesWithField("sex", jsonSchema["sex"])
        this.birthDateValidators =
            mapValidationRulesWithField("date_of_birth", jsonSchema["date_of_birth"])
        this.weightValidators = mapValidationRulesWithField("weight", jsonSchema["weight"])
        this.heightValidators = mapValidationRulesWithField("height", jsonSchema["height"])
    }

    fun validate(model: CompleteAccountStepRequest): Boolean {
        val sex = model.sex
        val sexFailedValidator = genderValidators?.firstOrNull { !it.isValid(sex) }
        sexFailedValidator?.error?.let {
            onGenderErrorChanged?.invoke(it)
        }

        val dateOfBirth = model.dateOfBirth
        val dateOfBirthFailedValidator =
            birthDateValidators?.firstOrNull { !it.isValid(dateOfBirth) }
        dateOfBirthFailedValidator?.error?.let {
            onBirthDateErrorChanged?.invoke(it)
        }

        val weight = model.weight
        val weightFailedValidator = weightValidators?.firstOrNull { !it.isValid(weight) }
        weightFailedValidator?.error?.let {
            onWeightErrorChanged?.invoke(it)
        }

        val height = model.height
        val heightFailedValidator = heightValidators?.firstOrNull { !it.isValid(height) }
        heightFailedValidator?.error?.let {
            onHeightErrorChanged?.invoke(it)
        }

        return sexFailedValidator == null &&
            dateOfBirthFailedValidator == null &&
            weightFailedValidator == null &&
            heightFailedValidator == null
    }
}
