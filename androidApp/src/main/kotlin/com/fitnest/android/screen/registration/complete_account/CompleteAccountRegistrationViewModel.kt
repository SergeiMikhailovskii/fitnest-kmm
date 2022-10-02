package com.fitnest.android.screen.registration.complete_account

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.domain.entity.RegistrationScreenState
import com.fitnest.domain.entity.RegistrationStepValidationSchema
import com.fitnest.domain.enum.SexType
import com.fitnest.domain.exception.CompleteAccountRegistrationScreenException
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.usecase.registration.SubmitRegistrationStepAndGetNextUseCase
import com.fitnest.domain.usecase.validation.CompleteAccountRegistrationValidationUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date

class CompleteAccountRegistrationViewModel(
    private val registrationScreenState: RegistrationScreenState,
    private val viewMapper: CompleteAccountRegistrationViewMapper,
    private val validator: CompleteAccountRegistrationValidationUseCase,
    private val submitRegistrationStepAndGetNextUseCase: SubmitRegistrationStepAndGetNextUseCase
) : BaseViewModel() {

    private var screenData = CompleteAccountRegistrationScreenData()

    private val _screenDataFlow = MutableStateFlow(screenData.copy())
    internal val screenDataFlow = _screenDataFlow.asStateFlow()

    override val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        if (throwable is CompleteAccountRegistrationScreenException) {
            screenData = screenData.copy(exception = throwable)
            updateScreen()
        } else if (throwable is Failure) {
            super.handleFailure(throwable)
        }
    }

    internal fun saveSex(sex: SexType) {
        screenData = screenData.copy(
            exception = screenData.exception.copy(genderError = null),
            sex = sex
        )
        updateScreen()
    }

    internal fun updateSexFocus(isFocused: Boolean) {
        screenData = screenData.copy(isSexFocused = isFocused)
        updateScreen()
    }

    internal fun saveBirthDate(date: Date) {
        screenData = screenData.copy(
            exception = screenData.exception.copy(birthDateError = null),
            dateOfBirth = date
        )
        updateScreen()
    }

    internal fun saveWeight(weight: Int) {
        screenData = screenData.copy(
            exception = screenData.exception.copy(weightError = null),
            weight = weight
        )
        updateScreen()
    }

    internal fun saveHeight(height: Int) {
        screenData = screenData.copy(
            exception = screenData.exception.copy(heightError = null),
            height = height
        )
        updateScreen()
    }

    internal fun submitRegistration() {
        viewModelScope.launch(exceptionHandler) {
            val request = viewMapper.mapScreenDataToStepRequestModel(screenData)

            val validationSchema = registrationScreenState.validationSchema
            if (validationSchema is RegistrationStepValidationSchema.CompleteAccountStepValidationSchema) {
                validator(request, validationSchema).getOrThrow()
            }

            val response = submitRegistrationStepAndGetNextUseCase(request).getOrThrow()
            response.step?.let { handleRoute(Route.RegistrationStep(it)) }
        }
    }

    private fun updateScreen() {
        _screenDataFlow.update { screenData.copy() }
    }
}