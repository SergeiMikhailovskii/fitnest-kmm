package com.fitnest.android.screen.registration.welcome_back

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.android.screen.registration.welcome_back.data.WelcomeBackRegistrationScreenData
import com.fitnest.domain.entity.RegistrationScreenState
import com.fitnest.domain.entity.RegistrationStepModel
import com.fitnest.domain.entity.request.WelcomeBackStepRequest
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.usecase.registration.SubmitRegistrationStepAndGetNext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WelcomeBackRegistrationViewModel(
    registrationScreenState: RegistrationScreenState,
    private val submitRegistrationStepAndGetNext: SubmitRegistrationStepAndGetNext
) : BaseViewModel() {

    private var screenData = WelcomeBackRegistrationScreenData()

    private val _screenDataFlow = MutableStateFlow(screenData.copy())
    internal val screenDataFlow = _screenDataFlow.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, failure ->
        if (failure is Failure.ValidationErrors && failure.fields.any { it.message == "registration.finished" }) {
            handleRoute(Route.Proxy())
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
            val response = submitRegistrationStepAndGetNext(WelcomeBackStepRequest()).getOrThrow()
            response.step?.let { handleRoute(Route.RegistrationStep(it)) }
        }
    }

    private fun updateScreen() {
        _screenDataFlow.update { screenData.copy() }
    }

}