package com.fitnest.domain.exception

data class CreateAccountRegistrationScreenException(
    var firstNameError: String? = null,
    var lastNameError: String? = null,
    var emailError: String? = null,
    var passwordError: String? = null,
)
