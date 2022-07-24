package com.fitnest.android.screen.registration.create_account

import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.android.screen.registration.RegistrationScreenState
import com.fitnest.domain.entity.RegistrationStepModel
import com.fitnest.domain.usecase.registration.SubmitRegistrationStepAndGetNext
import com.fitnest.domain.validator.CreateAccountRegistrationValidator
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class CreateAccountRegistrationViewModel(
    private val registrationScreenState: RegistrationScreenState,
    private val validator: CreateAccountRegistrationValidator,
    private val viewMapper: CreateAccountRegistrationViewMapper,
    private val submitRegistrationStepAndGetNext: SubmitRegistrationStepAndGetNext
) : BaseViewModel() {

    private var screenData = CreateAccountRegistrationScreenData()

    private val _screenDataFlow = MutableStateFlow(screenData.copy())
    internal val screenDataFlow = _screenDataFlow.asStateFlow()

    internal fun initializeStartData() {
        val fields = registrationScreenState.fields
        val validationSchema = registrationScreenState.validationSchema
        validationSchema?.let {
            validator.initialize(
                jsonSchema = it,
                onFirstNameErrorChanged = {
                    screenData = screenData.copy(
                        exception = screenData.exception.copy(firstNameError = it)
                    )
                },
                onLastNameErrorChanged = {
                    screenData = screenData.copy(
                        exception = screenData.exception.copy(lastNameError = it)
                    )
                },
                onEmailErrorChanged = {
                    screenData = screenData.copy(
                        exception = screenData.exception.copy(emailError = it)
                    )
                },
                onPasswordErrorChanged = {
                    screenData = screenData.copy(
                        exception = screenData.exception.copy(passwordError = it)
                    )
                }
            )
        }
        if (fields is RegistrationStepModel.CreateAccountStepModel) {
            setInitialScreenData(fields)
        }
        screenData = screenData.copy(validationSchema = validationSchema)
        updateScreenData()
    }

    internal fun updateFirstName(name: String) {
        screenData = screenData.copy(
            firstName = name,
            exception = screenData.exception.copy(firstNameError = null)
        )
        updateScreenData()
    }

    internal fun updateLastName(lastName: String) {
        screenData = screenData.copy(
            lastName = lastName,
            exception = screenData.exception.copy(lastNameError = null)
        )
        updateScreenData()
    }

    internal fun updateEmail(email: String) {
        screenData = screenData.copy(
            email = email,
            exception = screenData.exception.copy(emailError = null)
        )
        updateScreenData()
    }

    internal fun updatePassword(password: String) {
        screenData = screenData.copy(
            email = password,
            exception = screenData.exception.copy(passwordError = null)
        )
        updateScreenData()
    }

    internal fun changePasswordVisibility() {
        screenData = screenData.copy(
            passwordVisible = !screenData.passwordVisible,
        )
        updateScreenData()
    }

    internal fun updateFirstNameFocus(isFocused: Boolean) {
        screenData = screenData.copy(
            isFirstNameFocused = isFocused,
        )
        updateScreenData()
    }

    internal fun updateLastNameFocus(isFocused: Boolean) {
        screenData = screenData.copy(
            isLastNameFocused = isFocused,
        )
        updateScreenData()
    }

    internal fun updateEmailFocus(isFocused: Boolean) {
        screenData = screenData.copy(
            isEmailFocused = isFocused,
        )
        updateScreenData()
    }

    internal fun updatePasswordFocus(isFocused: Boolean) {
        screenData = screenData.copy(
            isPasswordFocused = isFocused,
        )
        updateScreenData()
    }

    internal fun submitRegistration() {
        val requestData = viewMapper.mapScreenDataToStepRequestModel(screenData)
        if (!validator.validate(requestData)) {
            updateScreenData()
            return
        }

        submitRegistrationStepAndGetNext(requestData) {
            it.either(::handleFailure) {
                registrationScreenState.fields = it?.fields
                registrationScreenState.validationSchema = it?.validationSchema
                it?.step?.let { handleRoute(Route.RegistrationStep(it)) }
            }
        }
    }

    internal fun navigateToLogin() {
        handleRoute(Route.Login)
    }

    internal fun handleGoogleSignIn(account: GoogleSignInAccount) {
        screenData = screenData.copy(
            firstName = account.givenName,
            lastName = account.familyName,
            email = account.email,
            password = account.idToken
        )
        updateScreenData()
        submitRegistration()
    }

    private fun setInitialScreenData(stepData: RegistrationStepModel.CreateAccountStepModel) {
        screenData = screenData.copy(
            firstName = stepData.firstName,
            lastName = stepData.lastName,
            email = stepData.email,
            password = stepData.password
        )
        updateScreenData()
    }

    private fun updateScreenData() {
        _screenDataFlow.update { screenData.copy() }
    }

}