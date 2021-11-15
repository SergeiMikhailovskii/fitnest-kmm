package com.fitnest.android.view.splash

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
                handleProgress(false)
            }
        }
    }

    internal fun navigateNext() {
        when (redirectFlow) {
            FlowType.ONBOARDING -> redirectToOnboarding()
            else -> {
                // will be added later
            }
        }
    }

    private fun handleGenerateTokenFailure(failure: Failure) {
        if (failure is Failure.ServerError && failure.responseCode == 401) {
            redirectToOnboarding()
        } else {
            handleFailure(failure)
        }
    }

    private fun redirectToOnboarding() {
        handleRoute(Route.Onboarding)
    }
}