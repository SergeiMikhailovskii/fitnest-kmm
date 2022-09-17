package com.fitnest.android.screen.proxy

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.android.screen.registration.RegistrationScreenState
import com.fitnest.domain.enum.FlowType
import com.fitnest.domain.usecase.GenerateTokenUseCase
import com.fitnest.domain.usecase.onboarding.GetOnboardingStep
import com.fitnest.domain.usecase.registration.GetRegistrationStepData
import kotlinx.coroutines.launch

internal class ProxyViewModel(
    private val generateTokenUseCase: GenerateTokenUseCase,
    private val getOnboardingStepUseCase: GetOnboardingStep,
    private val getRegistrationStepDataUseCase: GetRegistrationStepData,
    private val registrationScreenState: RegistrationScreenState,
) : BaseViewModel() {

    internal fun getNextFlow(flow: FlowType) {
        if (flow == FlowType.UNKNOWN) {
            viewModelScope.launch {
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
                getOnboardingStepUseCase {
                    it.either(::handleFailure) {
                        handleRoute(Route.OnboardingStep(stepName = it.orEmpty()))
                    }
                }
            }
            FlowType.REGISTRATION -> {
                getRegistrationStepDataUseCase {
                    it.either(::handleFailure) {
                        registrationScreenState.fields = it?.fields
                        registrationScreenState.validationSchema = it?.validationSchema
                        handleRoute(Route.RegistrationStep(stepName = it?.step.orEmpty()))
                    }
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