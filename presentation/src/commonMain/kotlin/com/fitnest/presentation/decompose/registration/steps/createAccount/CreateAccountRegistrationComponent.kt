package com.fitnest.presentation.decompose.registration.steps.createAccount

import com.arkivanov.decompose.value.Value

interface CreateAccountRegistrationComponent {
    val model: Value<Model>

    data class Model(
        val firstName: String = "",
        val lastName: String = "",
        val email: String = "",
        val password: String = "",
        val isPasswordVisible: Boolean = false
    )
}
