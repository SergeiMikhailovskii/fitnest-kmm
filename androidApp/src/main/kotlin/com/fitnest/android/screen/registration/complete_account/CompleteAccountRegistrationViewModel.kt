package com.fitnest.android.screen.registration.complete_account

import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.android.screen.registration.RegistrationScreenState
import com.fitnest.domain.enum.SexType
import com.fitnest.domain.usecase.SubmitRegistrationStepAndGetNext
import com.fitnest.domain.validator.CompleteAccountRegistrationValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*

class CompleteAccountRegistrationViewModel(
    private val registrationScreenState: RegistrationScreenState,
    private val viewMapper: CompleteAccountRegistrationViewMapper,
    private val screenData: CompleteAccountRegistrationScreenData,
    private val validator: CompleteAccountRegistrationValidator,
    private val submitRegistrationStepAndGetNext: SubmitRegistrationStepAndGetNext
) : BaseViewModel() {

    private val _screenDataFlow = MutableStateFlow(screenData.copy())
    internal val screenDataFlow = _screenDataFlow.asStateFlow()

    internal fun initializeStartData() {
        val validationSchema = registrationScreenState.validationSchema
        validationSchema?.let {
            validator.initialize(
                jsonSchema = it,
                onGenderErrorChanged = {
                    screenData.exception.genderError = it
                },
                onBirthDateErrorChanged = {
                    screenData.exception.birthDateError = it
                },
                onHeightErrorChanged = {
                    screenData.exception.heightError = it
                },
                onWeightErrorChanged = {
                    screenData.exception.weightError = it
                }
            )
        }
        screenData.validationSchema = validationSchema
        updateScreen()
    }


    internal fun saveSex(sex: SexType) {
        screenData.exception.genderError = null
        screenData.sex = sex
        updateScreen()
    }

    internal fun updateSexFocus(isFocused: Boolean) {
        screenData.isSexFocused = isFocused
        updateScreen()
    }

    internal fun saveBirthDate(date: Date) {
        screenData.exception.birthDateError = null
        screenData.dateOfBirth = date
        updateScreen()
    }

    internal fun saveWeight(weight: Int) {
        screenData.exception.weightError = null
        screenData.weight = weight
        updateScreen()
    }

    internal fun saveHeight(height: Int) {
        screenData.exception.heightError = null
        screenData.height = height
        updateScreen()
    }

    internal fun submitRegistration() {
        val request = viewMapper.mapScreenDataToStepRequestModel(screenData)
        if (!validator.validate(request)) {
            updateScreen()
            return
        }
        submitRegistrationStepAndGetNext(request) {
            it.either(::handleFailure) {
                registrationScreenState.fields = it?.fields
                registrationScreenState.validationSchema = it?.validationSchema
                it?.step?.let { handleRoute(Route.RegistrationStep(it)) }
            }
        }
    }

    private fun updateScreen() {
        _screenDataFlow.update { screenData.copy() }
    }
}