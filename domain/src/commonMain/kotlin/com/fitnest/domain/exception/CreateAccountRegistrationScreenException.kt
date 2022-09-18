package com.fitnest.domain.exception

data class CreateAccountRegistrationScreenException(
    val firstNameError: String? = null,
    val lastNameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
) : Throwable()
