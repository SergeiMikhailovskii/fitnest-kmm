package com.fitnest.android.view.splash

import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.domain.enum.FlowType
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.usecase.GenerateTokenUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class SplashViewModel(
    private val generateTokenUseCase: GenerateTokenUseCase
) : BaseViewModel() {

    internal fun generateToken() {
        generateTokenUseCase {
            it.either(::handleGenerateTokenFailure) {
                when (it?.getFlow()) {
                    FlowType.ONBOARDING -> redirectToOnboarding()
                    else -> {
                        // will be added later
                    }
                }
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