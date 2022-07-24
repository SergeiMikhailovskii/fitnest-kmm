package com.fitnest.android.screen.registration.create_account

import com.fitnest.domain.entity.validator.Validator
import com.fitnest.domain.exception.CreateAccountRegistrationScreenException

data class CreateAccountRegistrationScreenData(
    val firstName: String? = "",
    val lastName: String? = "",
    val email: String? = "",
    val password: String? = "",
    val passwordVisible: Boolean = false,
    val isFirstNameFocused: Boolean = false,
    val isLastNameFocused: Boolean = false,
    val isEmailFocused: Boolean = false,
    val isPasswordFocused: Boolean = false,
    val exception: CreateAccountRegistrationScreenException = CreateAccountRegistrationScreenException(),
    val validationSchema: Map<String, List<Validator?>>? = null
)
