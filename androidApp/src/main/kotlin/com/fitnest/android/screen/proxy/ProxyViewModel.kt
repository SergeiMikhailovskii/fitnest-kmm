package com.fitnest.android.screen.proxy

import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.domain.enum.FlowType
import com.fitnest.domain.usecase.GetOnboardingStep
import com.fitnest.domain.usecase.GetRegistrationStepData

class ProxyViewModel(
    private val getOnboardingStepUseCase: GetOnboardingStep,
    private val getRegistrationStepDataUseCase: GetRegistrationStepData,
) : BaseViewModel() {

    internal fun showNextScreen(flow: FlowType) {
        when (flow) {
            FlowType.ONBOARDING -> {
                getOnboardingStepUseCase {
                    it.either(::handleFailure) {
                        handleRoute(Route.OnboardingStep(it ?: ""))
                    }
                }
            }
            FlowType.REGISTRATION -> {
                getRegistrationStepDataUseCase {
                    it.either(::handleFailure) {
                        handleRoute(Route.RegistrationStep(it?.step ?: ""))
                    }
                }
            }
            else -> {
            }
        }
    }
}