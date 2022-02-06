package com.fitnest.domain.exception

data class CompleteAccountRegistrationScreenException(
    var genderError: String? = null,
    var birthDateError: String? = null,
    var weightError: String? = null,
    var heightError: String? = null
)
