package com.fitnest.domain.validator

import com.fitnest.domain.entity.request.CreateAccountStepRequest
import com.fitnest.domain.entity.validator.Validator

class CreateAccountRegistrationValidator : BaseValidator() {
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

        this.firstNameValidators =
            mapValidationRulesWithField("first_name", jsonSchema["first_name"])
        this.lastNameValidators = mapValidationRulesWithField("last_name", jsonSchema["last_name"])
        this.emailValidators = mapValidationRulesWithField("email", jsonSchema["email"])
        this.passwordValidators = mapValidationRulesWithField("password", jsonSchema["password"])
    }

    fun validate(model: CreateAccountStepRequest) {
        val firstName = model.firstName
        firstNameValidators?.firstOrNull { !it.isValid(firstName) }?.error?.let {
            onFirstNameErrorChanged?.invoke(it)
        }

        val lastName = model.lastName
        lastNameValidators?.firstOrNull { !it.isValid(lastName) }?.error?.let {
            onLastNameErrorChanged?.invoke(it)
        }

        val email = model.email
        emailValidators?.firstOrNull { !it.isValid(email) }?.error?.let {
            onEmailErrorChanged?.invoke(it)
        }

        val password = model.password
        passwordValidators?.firstOrNull { !it.isValid(password) }?.error?.let {
            onPasswordErrorChanged?.invoke(it)
        }
    }

}