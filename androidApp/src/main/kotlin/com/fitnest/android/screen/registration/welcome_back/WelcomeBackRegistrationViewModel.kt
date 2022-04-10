package com.fitnest.android.screen.registration.welcome_back

import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.screen.registration.RegistrationScreenState
import com.fitnest.android.screen.registration.welcome_back.data.WelcomeBackRegistrationScreenData
import com.fitnest.domain.entity.RegistrationStepModel
import com.fitnest.domain.usecase.SubmitRegistrationStepAndGetNext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WelcomeBackRegistrationViewModel(
    private val registrationScreenState: RegistrationScreenState,
    private val submitRegistrationStepAndGetNext: SubmitRegistrationStepAndGetNext
) : BaseViewModel() {

    private var screenData = WelcomeBackRegistrationScreenData()

    private val _screenDataFlow = MutableStateFlow(screenData.copy())
    internal val screenDataFlow = _screenDataFlow.asStateFlow()

    init {
        val fields = registrationScreenState.fields
        if (fields is RegistrationStepModel.WelcomeBackStepModel) {
            screenData = screenData.copy(name = fields.name)
        }
        updateScreen()
    }

    internal fun next() {

    }

    private fun updateScreen() {
        _screenDataFlow.update { screenData.copy() }
    }

}