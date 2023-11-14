package com.fitnest.presentation.decompose.registration.steps.createAccount

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class DefaultCreateAccountRegistrationComponent : CreateAccountRegistrationComponent {

    private val _model = MutableValue(CreateAccountRegistrationComponent.Model())
    override val model: Value<CreateAccountRegistrationComponent.Model> = _model

    override fun setFirstName(name: String) {
        _model.value = _model.value.copy(firstName = name)
    }

    override fun setLastName(lastName: String) {
        _model.value = _model.value.copy(lastName = lastName)
    }

    override fun setEmail(email: String) {
        _model.value = _model.value.copy(email = email)
    }

    override fun setPassword(password: String) {
        _model.value = _model.value.copy(password = password)
    }

    override fun changePasswordVisibility() {
        _model.value = _model.value.copy(isPasswordVisible = !_model.value.isPasswordVisible)
    }

    override fun submitRegistration() {
    }

    override fun navigateToLogin() {
    }
}
