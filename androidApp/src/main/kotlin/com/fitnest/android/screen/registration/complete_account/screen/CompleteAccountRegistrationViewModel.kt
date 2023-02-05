package com.fitnest.android.screen.registration.complete_account.screen

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.android.screen.registration.complete_account.anthropometry.AnthropometryEvent
import com.fitnest.android.screen.registration.complete_account.anthropometry.AnthropometryEventsBusSubscriber
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

internal class CompleteAccountRegistrationViewModel(
    private val registrationScreenState: RegistrationScreenState,
    private val viewMapper: CompleteAccountRegistrationViewMapper,
    private val validator: CompleteAccountRegistrationValidationUseCase,
    private val submitRegistrationStepAndGetNextUseCase: SubmitRegistrationStepAndGetNextUseCase,
    private val anthropometryEventsBus: AnthropometryEventsBusSubscriber
) : BaseViewModel() {

    private var screenData = CompleteAccountRegistrationScreenData()

    private val _screenDataFlow = MutableStateFlow(screenData.copy())
    val screenDataFlow = _screenDataFlow.asStateFlow()

    init {
        viewModelScope.launch {
            anthropometryEventsBus.subscribe {
                if (it is AnthropometryEvent.Submit) {
                    saveWeight(it.value)
                }
            }
        }
    }

    override val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        if (throwable is CompleteAccountRegistrationScreenException) {
            screenData = screenData.copy(exception = throwable)
            updateScreen()
        } else if (throwable is Failure) {
            super.handleFailure(throwable)
        }
    }

    fun saveSex(sex: SexType) {
        screenData = screenData.copy(
            exception = screenData.exception.copy(genderError = null),
            sex = sex
        )
        updateScreen()
    }

    fun updateSexFocus(isFocused: Boolean) {
        screenData = screenData.copy(isSexFocused = isFocused)
        updateScreen()
    }

    fun saveBirthDate(date: Date) {
        screenData = screenData.copy(
            exception = screenData.exception.copy(birthDateError = null),
            dateOfBirth = date
        )
        updateScreen()
    }

    fun saveWeight(weight: Int) {
        screenData = screenData.copy(
            exception = screenData.exception.copy(weightError = null),
            weight = weight
        )
        updateScreen()
    }

    fun saveHeight(height: Int) {
        screenData = screenData.copy(
            exception = screenData.exception.copy(heightError = null),
            height = height
        )
        updateScreen()
    }

    fun submitRegistration() {
        viewModelScope.launch(exceptionHandler) {
            val request = viewMapper.mapScreenDataToStepRequestModel(screenData)

            val validationSchema = registrationScreenState.validationSchema
            if (validationSchema is RegistrationStepValidationSchema.CompleteAccountStepValidationSchema) {
                validator(request, validationSchema).getOrThrow()
            }

            val response = submitRegistrationStepAndGetNextUseCase(request).getOrThrow()
            response.step?.let { handleRoute(Route.Registration.Step(it)) }
        }
    }

    fun openWeightBottomSheet() {
        handleRoute(
            Route.Registration.AnthropometryBottomSheet(
                minValue = 0,
                maxValue = 200,
                initialValue = 70
            )
        )
    }

    private fun updateScreen() {
        _screenDataFlow.update { screenData.copy() }
    }
}