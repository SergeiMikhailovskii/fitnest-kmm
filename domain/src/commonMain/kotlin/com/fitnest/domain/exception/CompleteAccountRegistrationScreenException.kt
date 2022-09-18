package com.fitnest.domain.exception

data class CompleteAccountRegistrationScreenException(
    val genderError: String? = null,
    val birthDateError: String? = null,
    val weightError: String? = null,
    val heightError: String? = null
) : Throwable()
