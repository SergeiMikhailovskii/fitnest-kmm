package com.fitnest.presentation.screen.registration.createAccount

import com.fitnest.domain.entity.RegistrationScreenState
import com.fitnest.domain.entity.RegistrationStepModel
import com.fitnest.domain.entity.RegistrationStepValidationSchema
import com.fitnest.domain.entity.response.FacebookLoginResponse
import com.fitnest.domain.entity.thirdparty.FitnestSignInAccount
import com.fitnest.domain.exception.CreateAccountRegistrationScreenException
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.usecase.registration.SubmitRegistrationStepAndGetNextUseCase
import com.fitnest.domain.usecase.validation.CreateAccountRegistrationValidationUseCase
import com.fitnest.presentation.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateAccountRegistrationViewModel(
    private val registrationScreenState: RegistrationScreenState,
    private val validator: CreateAccountRegistrationValidationUseCase,
    private val viewMapper: CreateAccountRegistrationViewMapper,
    private val submitRegistrationStepAndGetNextUseCase: SubmitRegistrationStepAndGetNextUseCase
) : BaseViewModel() {

    private var screenData = CreateAccountRegistrationScreenData()

    private val _screenDataFlow = MutableStateFlow(screenData.copy())
    val screenDataFlow = _screenDataFlow.asStateFlow()

    override val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        if (throwable is CreateAccountRegistrationScreenException) {
            screenData = screenData.copy(exception = throwable)
            updateScreenData()
        } else if (throwable is Failure) {
            super.handleFailure(throwable)
        }
    }

    fun initializeStartData() {
        val fields = registrationScreenState.fields
        if (fields is RegistrationStepModel.CreateAccountStepModel) {
            setInitialScreenData(fields)
        }
        updateScreenData()
    }

    fun updateFirstName(name: String) {
        screenData = screenData.copy(
            firstName = name,
            exception = screenData.exception.copy(firstNameError = null)
        )
        updateScreenData()
    }

    fun updateLastName(lastName: String) {
        screenData = screenData.copy(
            lastName = lastName,
            exception = screenData.exception.copy(lastNameError = null)
        )
        updateScreenData()
    }

    fun updateEmail(email: String) {
        screenData = screenData.copy(
            email = email,
            exception = screenData.exception.copy(emailError = null)
        )
        updateScreenData()
    }

    fun updatePassword(password: String) {
        screenData = screenData.copy(
            password = password,
            exception = screenData.exception.copy(passwordError = null)
        )
        updateScreenData()
    }

    fun changePasswordVisibility() {
        screenData = screenData.copy(
            passwordVisible = !screenData.passwordVisible
        )
        updateScreenData()
    }

    fun submitRegistration() {
        viewModelScope.launch(exceptionHandler) {
            val requestData = viewMapper.mapScreenDataToStepRequestModel(screenData)

            val validationSchema = registrationScreenState.validationSchema
            if (validationSchema is RegistrationStepValidationSchema.CreateAccountStepValidationSchema) {
                validator(requestData, validationSchema).getOrThrow()
            }

            val response = submitRegistrationStepAndGetNextUseCase(requestData).getOrThrow()
            response.step?.let { handleRoute(com.fitnest.presentation.navigation.Route.Registration.Step(it)) }
        }
    }

    fun navigateToLogin() {
        handleRoute(com.fitnest.presentation.navigation.Route.Login)
    }

    fun handleGoogleSignIn(account: FitnestSignInAccount) {
        screenData = screenData.copy(
            firstName = account.givenName,
            lastName = account.familyName,
            email = account.email,
            password = account.idToken
        )
        updateScreenData()
        submitRegistration()
    }

    fun handleFacebookSignIn(account: FacebookLoginResponse) {
        screenData = screenData.copy(
            firstName = account.firstName,
            lastName = account.lastName,
            email = account.email,
            password = account.id
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
