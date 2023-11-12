package com.fitnest.presentation.decompose.proxy

import com.arkivanov.decompose.ComponentContext
import com.fitnest.domain.enum.FlowType
import com.fitnest.domain.usecase.onboarding.GetOnboardingStepUseCase
import com.fitnest.domain.usecase.proxy.GetFlowUseCase
import com.fitnest.domain.usecase.registration.GetRegistrationStepData
import com.fitnest.presentation.extension.disposableScope
import com.fitnest.presentation.navigation.Route
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DefaultProxyComponent(
    context: ComponentContext,
    flowType: FlowType = FlowType.UNKNOWN,
    private val getFlowUseCase: GetFlowUseCase,
    private val getOnboardingStepUseCase: GetOnboardingStepUseCase,
    private val getRegistrationStepDataUseCase: GetRegistrationStepData,
    private val onNavigate: (Route) -> Unit
) : ProxyComponent, CoroutineScope by context.disposableScope() {

    init {
        launch {
            val flow = if (flowType == FlowType.UNKNOWN) {
                getFlowUseCase()
            } else {
                flowType
            }

            showNextScreen(flow)
        }
    }

    private suspend fun showNextScreen(flow: FlowType) {
        when (flow) {
            FlowType.ONBOARDING -> {
                val response = getOnboardingStepUseCase().getOrThrow()
                response?.let { onNavigate(Route.Onboarding(it)) }
            }

            FlowType.REGISTRATION -> {
                val response = getRegistrationStepDataUseCase().getOrThrow()
                onNavigate(Route.Registration2(response.step.orEmpty()))
            }

            FlowType.MAIN -> {
                onNavigate(Route.PrivateArea.Home)
            }

            else -> {
            }
        }
    }
}
