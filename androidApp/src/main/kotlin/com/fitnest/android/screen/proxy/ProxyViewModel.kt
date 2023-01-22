package com.fitnest.android.screen.proxy

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.domain.enum.FlowType
import com.fitnest.domain.usecase.GenerateTokenUseCase
import com.fitnest.domain.usecase.onboarding.GetOnboardingStepUseCase
import com.fitnest.domain.usecase.registration.GetRegistrationStepData
import kotlinx.coroutines.launch

internal class ProxyViewModel(
    private val generateTokenUseCase: GenerateTokenUseCase,
    private val getOnboardingStepUseCase: GetOnboardingStepUseCase,
    private val getRegistrationStepDataUseCase: GetRegistrationStepData,
) : BaseViewModel() {

    internal fun getNextFlow(flow: FlowType) {
        if (flow == FlowType.UNKNOWN) {
            viewModelScope.launch(exceptionHandler) {
                val response = generateTokenUseCase().getOrThrow()
                showNextScreen(response.getFlow())
            }
        } else {
            showNextScreen(flow)
        }
    }

    private fun showNextScreen(flow: FlowType) {
        when (flow) {
            FlowType.ONBOARDING -> {
                viewModelScope.launch(exceptionHandler) {
                    val response = getOnboardingStepUseCase().getOrThrow()
                    response?.let { handleRoute(Route.OnboardingStep(it)) }
                }
            }
            FlowType.REGISTRATION -> {
                viewModelScope.launch(exceptionHandler) {
                    val response = getRegistrationStepDataUseCase().getOrThrow()
                    handleRoute(Route.RegistrationStep(stepName = response.step.orEmpty()))
                }
            }
            FlowType.MAIN -> {
                handleRoute(Route.PrivateAreaHome)
            }
            else -> {
            }
        }
    }
}
