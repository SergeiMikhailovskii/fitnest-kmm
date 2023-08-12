package com.fitnest.presentation.screen.registration.completeAccount.screen

import com.fitnest.domain.entity.RegistrationScreenState
import com.fitnest.domain.entity.RegistrationStepValidationSchema
import com.fitnest.domain.enum.SexType
import com.fitnest.domain.exception.CompleteAccountRegistrationScreenException
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.internal.date.Date
import com.fitnest.domain.usecase.registration.SubmitRegistrationStepAndGetNextUseCase
import com.fitnest.domain.usecase.validation.CompleteAccountRegistrationValidationUseCase
import com.fitnest.presentation.base.BaseViewModel
import com.fitnest.presentation.enumType.CompleteAccountRegistrationScreenBottomSheetType
import com.fitnest.presentation.navigation.Route.Registration.AnthropometryBottomSheet
import com.fitnest.presentation.screen.registration.completeAccount.anthropometry.AnthropometryEvent
import com.fitnest.presentation.screen.registration.completeAccount.anthropometry.AnthropometryEventsBusSubscriber
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CompleteAccountRegistrationViewModel(
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
            anthropometryEventsBus.subscribe(::handleEvent)
        }
    }

    private fun handleEvent(event: AnthropometryEvent) {
        if (event is AnthropometryEvent.Submit) {
            if (screenData.currentAnthropometryType == CompleteAccountRegistrationScreenBottomSheetType.WEIGHT) {
                saveWeight(event.value)
            } else {
                saveHeight(event.value)
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

    fun submitRegistration() {
        viewModelScope.launch(exceptionHandler) {
            handleProgress(true)
            val request = viewMapper.mapScreenDataToStepRequestModel(screenData)

            val validationSchema = registrationScreenState.validationSchema
            if (validationSchema is RegistrationStepValidationSchema.CompleteAccountStepValidationSchema) {
                validator(request, validationSchema).getOrThrow()
            }

            val response = submitRegistrationStepAndGetNextUseCase(request).getOrThrow()
            handleProgress()
            response.step?.let { handleRoute(com.fitnest.presentation.navigation.Route.Registration.Step(it)) }
        }
    }

    fun openWeightBottomSheet() {
        screenData = screenData.copy(
            currentAnthropometryType = CompleteAccountRegistrationScreenBottomSheetType.WEIGHT
        )
        handleRoute(
            AnthropometryBottomSheet(
                minValue = 0,
                maxValue = 200,
                initialValue = 70
            )
        )
    }

    fun openHeightBottomSheet() {
        screenData = screenData.copy(
            currentAnthropometryType = CompleteAccountRegistrationScreenBottomSheetType.HEIGHT
        )
        handleRoute(
            AnthropometryBottomSheet(
                minValue = 0,
                maxValue = 220,
                initialValue = 188
            )
        )
    }

    private fun saveWeight(weight: Int) {
        screenData = screenData.copy(
            exception = screenData.exception.copy(weightError = null),
            weight = weight
        )
        updateScreen()
    }

    private fun saveHeight(height: Int) {
        screenData = screenData.copy(
            exception = screenData.exception.copy(heightError = null),
            height = height
        )
        updateScreen()
    }

    private fun updateScreen() {
        _screenDataFlow.update { screenData.copy() }
    }
}
