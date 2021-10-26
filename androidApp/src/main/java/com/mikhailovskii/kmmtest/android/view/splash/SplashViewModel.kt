package com.mikhailovskii.kmmtest.android.view.splash

import androidx.lifecycle.ViewModel
import com.mikhailovskii.kmmtest.usecase.GenerateTokenUseCase

class SplashViewModel(private val generateTokenUseCase: GenerateTokenUseCase) : ViewModel() {

    internal fun generateToken() {
        generateTokenUseCase {

        }
    }
}