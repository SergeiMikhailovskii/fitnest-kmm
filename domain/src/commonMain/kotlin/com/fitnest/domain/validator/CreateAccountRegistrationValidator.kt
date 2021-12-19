package com.fitnest.domain.validator

import com.fitnest.domain.entity.request.CreateAccountStepRequest
import com.fitnest.domain.entity.validator.Validator

class CreateAccountRegistrationValidator {
    private var onFirstNameErrorChanged: ((String) -> Unit)? = null
    private var onLastNameErrorChanged: ((String) -> Unit)? = null
    private var onEmailErrorChanged: ((String) -> Unit)? = null
    private var onPasswordErrorChanged: ((String) -> Unit)? = null

    private var firstNameValidators: List<Validator>? = null
    private var lastNameValidators: List<Validator>? = null
    private var emailValidators: List<Validator>? = null
    private var passwordValidators: List<Validator>? = null

    fun initialize(
        jsonSchema: Map<String, List<Validator>>,
        onFirstNameErrorChanged: (String) -> Unit,
        onLastNameErrorChanged: (String) -> Unit,
        onEmailErrorChanged: (String) -> Unit,
        onPasswordErrorChanged: (String) -> Unit,
    ) {
        this.onFirstNameErrorChanged = onFirstNameErrorChanged
        this.onLastNameErrorChanged = onLastNameErrorChanged
        this.onEmailErrorChanged = onEmailErrorChanged
        this.onPasswordErrorChanged = onPasswordErrorChanged

        this.firstNameValidators = jsonSchema["first_name"]
        this.lastNameValidators = jsonSchema["last_name"]
        this.emailValidators = jsonSchema["email"]
        this.passwordValidators = jsonSchema["password"]
    }

    fun validate(model: CreateAccountStepRequest) {
        val firstName = model.firstName
        val failedFirstNameValidator = firstNameValidators?.firstOrNull { !it.validate(firstName) }
        println()
    }

}