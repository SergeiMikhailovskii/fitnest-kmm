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
        handleRoute(Route.Proxy)
    }

    private fun handleGenerateTokenFailure(failure: Failure) {
        handleProgress()
        if (failure is Failure.ServerError && failure.responseCode == 401) {
            redirectFlow = FlowType.ONBOARDING
        } else {
            handleFailure(failure)
        }
    }
}