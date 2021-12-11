package com.fitnest.android.screen.splash

import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.domain.enum.FlowType
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.usecase.GenerateTokenUseCase

class SplashViewModel(
    private val generateTokenUseCase: GenerateTokenUseCase
) : BaseViewModel() {

    private var redirectFlow: FlowType? = null

    internal fun generateToken() {
        generateTokenUseCase {
            it.either(::handleGenerateTokenFailure) {
                redirectFlow = it?.getFlow()
                handleProgress()
            }
        }
    }

    internal fun navigateNext() {
        when (redirectFlow) {
            FlowType.ONBOARDING -> redirectToOnboarding()
            FlowType.REGISTRATION -> redirectToRegistration()
            else -> {
                // will be added later
            }
        }
    }

    private fun handleGenerateTokenFailure(failure: Failure) {
        handleProgress()
        if (failure is Failure.ServerError && failure.responseCode == 401) {
            redirectFlow = FlowType.ONBOARDING
        } else {
            handleFailure(failure)
        }
    }

    private fun redirectToOnboarding() {
        handleRoute(Route.Proxy(FlowType.ONBOARDING.name))
    }

    private fun redirectToRegistration() {
        handleRoute(Route.Proxy(FlowType.REGISTRATION.name))
    }
}