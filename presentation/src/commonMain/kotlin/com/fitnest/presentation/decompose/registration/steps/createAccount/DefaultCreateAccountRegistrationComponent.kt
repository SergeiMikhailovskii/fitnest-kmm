package com.fitnest.presentation.decompose.registration.steps.createAccount

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.fitnest.domain.entity.RegistrationScreenState
import com.fitnest.domain.entity.RegistrationStepValidationSchema
import com.fitnest.domain.exception.CreateAccountRegistrationScreenException
import com.fitnest.domain.usecase.registration.SubmitRegistrationStepAndGetNextUseCase
import com.fitnest.domain.usecase.validation.CreateAccountRegistrationValidationUseCase
import com.fitnest.presentation.extension.disposableScope
import com.fitnest.presentation.navigation.Route
import com.fitnest.presentation.screen.registration.createAccount.CreateAccountRegistrationViewMapper
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DefaultCreateAccountRegistrationComponent(
    context: ComponentContext,
    private val viewMapper: CreateAccountRegistrationViewMapper,
    private val submitRegistrationStepAndGetNextUseCase: SubmitRegistrationStepAndGetNextUseCase,
    private val registrationScreenState: RegistrationScreenState,
    private val validator: CreateAccountRegistrationValidationUseCase,
    private val onNavigate: (Route) -> Unit
) : CreateAccountRegistrationComponent,
    ComponentContext by context,
    CoroutineScope by context.disposableScope() {

    private val _model = MutableValue(CreateAccountRegistrationComponent.Model())
    override val model: Value<CreateAccountRegistrationComponent.Model> = _model

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        if (throwable is CreateAccountRegistrationScreenException) {
            _model.value = _model.value.copy(exception = throwable)
        }
    }

    override fun setFirstName(name: String) {
        _model.value = _model.value.copy(
            firstName = name,
            exception = _model.value.exception.copy(firstNameError = null)
        )
    }

    override fun setLastName(lastName: String) {
        _model.value = _model.value.copy(
            lastName = lastName,
            exception = _model.value.exception.copy(lastNameError = null)
        )
    }

    override fun setEmail(email: String) {
        _model.value = _model.value.copy(
            email = email,
            exception = _model.value.exception.copy(emailError = null)
        )
    }

    override fun setPassword(password: String) {
        _model.value = _model.value.copy(
            password = password,
            exception = _model.value.exception.copy(passwordError = null)
        )
    }

    override fun changePasswordVisibility() {
        _model.value = _model.value.copy(isPasswordVisible = !_model.value.isPasswordVisible)
    }

    override fun submitRegistration() {
        launch(exceptionHandler) {
            val requestData = viewMapper.mapScreenDataToStepRequestModel(_model.value)

            val validationSchema = registrationScreenState.validationSchema
            if (validationSchema is RegistrationStepValidationSchema.CreateAccountStepValidationSchema) {
                validator(requestData, validationSchema).getOrThrow()
            }

            val response = submitRegistrationStepAndGetNextUseCase(requestData).getOrThrow()
            response.step?.let { onNavigate(Route.Registration2.Step(it)) }
        }
    }

    override fun navigateToLogin() {
    }
}
