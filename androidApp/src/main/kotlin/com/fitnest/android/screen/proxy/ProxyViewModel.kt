package com.fitnest.android.screen.proxy

import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.domain.entity.RegistrationStepModel
import com.fitnest.domain.enum.FlowType
import com.fitnest.domain.usecase.GetOnboardingStep
import com.fitnest.domain.usecase.GetRegistrationStepData
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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
                        val fields = it?.fields
                        handleRoute(
                            Route.RegistrationStep(
                                stepName = it?.step ?: "",
                                data = if (fields != null) Json.encodeToString(
                                    fields
                                ) else null
                            )
                        )
                    }
                }
            }
            else -> {
            }
        }
    }
}