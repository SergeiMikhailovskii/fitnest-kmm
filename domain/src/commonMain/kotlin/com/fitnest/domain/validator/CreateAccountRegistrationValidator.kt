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

    fun validate(model: CreateAccountStepRequest): Boolean {
        val firstName = model.firstName
        val firstNameFailedValidator = firstNameValidators?.firstOrNull { !it.isValid(firstName) }
        firstNameFailedValidator?.error?.let {
            onFirstNameErrorChanged?.invoke(it)
        }

        val lastName = model.lastName
        val lastNameFailedValidator = lastNameValidators?.firstOrNull { !it.isValid(lastName) }
        lastNameFailedValidator?.error?.let {
            onLastNameErrorChanged?.invoke(it)
        }

        val email = model.email
        val emailFailedValidator = emailValidators?.firstOrNull { !it.isValid(email) }
        emailFailedValidator?.error?.let {
            onEmailErrorChanged?.invoke(it)
        }

        val password = model.password
        val passwordFailedValidator = passwordValidators?.firstOrNull { !it.isValid(password) }
        passwordFailedValidator?.error?.let {
            onPasswordErrorChanged?.invoke(it)
        }

        return firstNameFailedValidator == null
                && lastNameFailedValidator == null
                && emailFailedValidator == null
                && passwordFailedValidator == null
    }

}