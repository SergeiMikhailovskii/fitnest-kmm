package com.fitnest.presentation.screen.proxy

import com.fitnest.domain.enum.FlowType
import com.fitnest.domain.usecase.onboarding.GetOnboardingStepUseCase
import com.fitnest.domain.usecase.proxy.GetFlowUseCase
import com.fitnest.domain.usecase.registration.GetRegistrationStepData
import com.fitnest.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class ProxyViewModel(
    private val getFlowUseCase: GetFlowUseCase,
    private val getOnboardingStepUseCase: GetOnboardingStepUseCase,
    private val getRegistrationStepDataUseCase: GetRegistrationStepData
) : BaseViewModel() {

    fun getNextFlow(flow: FlowType) {
        if (flow == FlowType.UNKNOWN) {
            viewModelScope.launch(exceptionHandler) {
                val flowResponse = getFlowUseCase().getOrThrow()
                showNextScreen(flowResponse)
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
                    response?.let { handleRoute(com.fitnest.presentation.navigation.Route.OnboardingStep(it)) }
                }
            }

            FlowType.REGISTRATION -> {
                viewModelScope.launch(exceptionHandler) {
                    val response = getRegistrationStepDataUseCase().getOrThrow()
                    handleRoute(com.fitnest.presentation.navigation.Route.Registration.Step(stepName = response.step.orEmpty()))
                }
            }

            FlowType.MAIN -> {
                handleRoute(com.fitnest.presentation.navigation.Route.PrivateArea.Home)
            }

            else -> {
            }
        }
    }
}
