package com.fitnest.presentation.decompose.registration.steps.createAccount

import com.arkivanov.decompose.value.Value
import com.fitnest.domain.exception.CreateAccountRegistrationScreenException

interface CreateAccountRegistrationComponent {
    val model: Value<Model>

    fun setFirstName(name: String)
    fun setLastName(lastName: String)
    fun setEmail(email: String)
    fun setPassword(password: String)
    fun changePasswordVisibility()
    fun submitRegistration()
    fun navigateToLogin()

    data class Model(
        val firstName: String = "",
        val lastName: String = "",
        val email: String = "",
        val password: String = "",
        val isPasswordVisible: Boolean = false,
        val exception: CreateAccountRegistrationScreenException = CreateAccountRegistrationScreenException()
    )
}
