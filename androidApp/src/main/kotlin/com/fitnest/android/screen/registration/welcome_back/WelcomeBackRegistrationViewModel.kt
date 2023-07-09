package com.fitnest.android.screen.registration.welcome_back

import com.fitnest.android.screen.registration.welcome_back.data.WelcomeBackRegistrationScreenData
import com.fitnest.domain.entity.RegistrationScreenState
import com.fitnest.domain.entity.RegistrationStepModel
import com.fitnest.domain.entity.request.WelcomeBackStepRequest
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.usecase.registration.SubmitRegistrationStepAndGetNextUseCase
import com.fitnest.presentation.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WelcomeBackRegistrationViewModel(
    registrationScreenState: RegistrationScreenState,
    private val submitRegistrationStepAndGetNextUseCase: SubmitRegistrationStepAndGetNextUseCase
) : BaseViewModel() {

    private var screenData = WelcomeBackRegistrationScreenData()

    private val _screenDataFlow = MutableStateFlow(screenData.copy())
    internal val screenDataFlow = _screenDataFlow.asStateFlow()

    override val exceptionHandler = CoroutineExceptionHandler { _, failure ->
        if (failure is Failure.ValidationErrors && failure.fields.any { it.message == "registration.finished" }) {
            handleRoute(com.fitnest.presentation.navigation.Route.Proxy())
        } else if (failure is Failure) {
            super.handleFailure(failure)
        }
    }

    init {
        val fields = registrationScreenState.fields
        if (fields is RegistrationStepModel.WelcomeBackStepModel) {
            screenData = screenData.copy(name = fields.name)
        }
        updateScreen()
    }

    internal fun next() {
        viewModelScope.launch(exceptionHandler) {
            val response =
                submitRegistrationStepAndGetNextUseCase(WelcomeBackStepRequest()).getOrThrow()
            response.step?.let { handleRoute(com.fitnest.presentation.navigation.Route.Registration.Step(it)) }
        }
    }

    private fun updateScreen() {
        _screenDataFlow.update { screenData.copy() }
    }

}