package com.fitnest.android.screen.registration.create_account

import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.screen.registration.RegistrationScreenState
import com.fitnest.domain.entity.RegistrationStepModel
import com.fitnest.domain.usecase.SubmitRegistrationStep
import com.fitnest.domain.validator.CreateAccountRegistrationValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CreateAccountRegistrationViewModel(
    private val registrationScreenState: RegistrationScreenState,
    private val validator: CreateAccountRegistrationValidator,
    private val viewMapper: CreateAccountRegistrationViewMapper,
    private val submitRegistrationStepUseCase: SubmitRegistrationStep
) : BaseViewModel() {

    private val screenData = CreateAccountRegistrationScreenData.init()

    private val _screenDataFlow = MutableStateFlow(screenData.copy())
    internal val screenDataFlow = _screenDataFlow.asStateFlow()

    internal fun initializeStartData() {
        val fields = registrationScreenState.fields
        val validationSchema = registrationScreenState.validationSchema
        validationSchema?.let {
            validator.initialize(
                jsonSchema = it,
                onFirstNameErrorChanged = {
                    screenData.exception.firstNameError = it
                },
                onLastNameErrorChanged = {
                    screenData.exception.lastNameError = it
                },
                onEmailErrorChanged = {
                    screenData.exception.emailError = it
                },
                onPasswordErrorChanged = {
                    screenData.exception.passwordError = it
                }
            )
        }
        if (fields is RegistrationStepModel.CreateAccountStepModel) {
            setInitialScreenData(fields)
        }
        screenData.validationSchema = validationSchema
        updateScreenData()
    }

    internal fun updateFirstName(name: String) {
        screenData.firstName = name
        screenData.exception.firstNameError = null
        updateScreenData()
    }

    internal fun updateLastName(lastName: String) {
        screenData.lastName = lastName
        screenData.exception.lastNameError = null
        updateScreenData()
    }

    internal fun updateEmail(email: String) {
        screenData.email = email
        screenData.exception.emailError = null
        updateScreenData()
    }

    internal fun updatePassword(password: String) {
        screenData.password = password
        screenData.exception.passwordError = null
        updateScreenData()
    }

    internal fun changePasswordVisibility() {
        screenData.passwordVisible = !screenData.passwordVisible
        updateScreenData()
    }

    internal fun updateFirstNameFocus(isFocused: Boolean) {
        screenData.isFirstNameFocused = isFocused
        updateScreenData()
    }

    internal fun updateLastNameFocus(isFocused: Boolean) {
        screenData.isLastNameFocused = isFocused
        updateScreenData()
    }

    internal fun updateEmailFocus(isFocused: Boolean) {
        screenData.isEmailFocused = isFocused
        updateScreenData()
    }

    internal fun updatePasswordFocus(isFocused: Boolean) {
        screenData.isPasswordFocused = isFocused
        updateScreenData()
    }

    internal fun submitRegistration() {
        val requestData = viewMapper.mapScreenDataToStepRequestModel(screenData)
        if (!validator.validate(requestData)) {
            updateScreenData()
            return
        }

        submitRegistrationStepUseCase(requestData) {
            it.either(::handleFailure) {
                println()
            }
        }
    }

    private fun setInitialScreenData(stepData: RegistrationStepModel.CreateAccountStepModel) {
        screenData.firstName = stepData.firstName
        screenData.lastName = stepData.lastName
        screenData.email = stepData.email
        screenData.password = stepData.password
        updateScreenData()
    }

    private fun updateScreenData() {
        _screenDataFlow.update { screenData.copy() }
    }

}