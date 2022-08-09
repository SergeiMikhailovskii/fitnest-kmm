package com.fitnest.domain.exception

data class LoginPageValidationException(
    val loginError: String? = null,
    val passwordError: String? = null
) : Throwable()
