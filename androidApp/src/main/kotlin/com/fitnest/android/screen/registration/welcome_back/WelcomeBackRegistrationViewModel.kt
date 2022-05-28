package com.fitnest.android.screen.registration.welcome_back

import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.android.screen.registration.RegistrationScreenState
import com.fitnest.android.screen.registration.welcome_back.data.WelcomeBackRegistrationScreenData
import com.fitnest.domain.entity.RegistrationStepModel
import com.fitnest.domain.entity.request.WelcomeBackStepRequest
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.usecase.registration.SubmitRegistrationStepAndGetNext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WelcomeBackRegistrationViewModel(
    registrationScreenState: RegistrationScreenState,
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
        submitRegistrationStepAndGetNext(WelcomeBackStepRequest) {
            it.either(::handleRegistrationFailure) {
                it?.step?.let { handleRoute(Route.RegistrationStep(it)) }
            }
        }
    }

    private fun handleRegistrationFailure(failure: Failure?) {
        if (failure is Failure.ValidationError && failure.message == "registration.finished") {
            handleRoute(Route.Proxy)
        }
    }

    private fun updateScreen() {
        _screenDataFlow.update { screenData.copy() }
    }

}