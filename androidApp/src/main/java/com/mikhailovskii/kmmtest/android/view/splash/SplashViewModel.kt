package com.mikhailovskii.kmmtest.android.view.splash

import com.mikhailovskii.kmmtest.android.base.BaseViewModel
import com.fitnest.domain.usecase.GenerateTokenUseCase

class SplashViewModel(
    private val generateTokenUseCase: GenerateTokenUseCase
) : BaseViewModel() {

    internal fun generateToken() {
        generateTokenUseCase {
            it.either(::handleFailure) {

            }
        }
    }
}