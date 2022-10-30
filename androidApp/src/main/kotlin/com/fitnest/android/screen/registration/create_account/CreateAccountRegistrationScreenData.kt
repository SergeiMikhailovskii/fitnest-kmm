package com.fitnest.android.screen.registration.create_account

import com.fitnest.domain.exception.CreateAccountRegistrationScreenException

data class CreateAccountRegistrationScreenData(
    val firstName: String? = "",
    val lastName: String? = "",
    val email: String? = "",
    val password: String? = "",
    val passwordVisible: Boolean = false,
    val exception: CreateAccountRegistrationScreenException = CreateAccountRegistrationScreenException(),
)
