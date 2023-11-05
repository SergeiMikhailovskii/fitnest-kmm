package com.fitnest.presentation.screen.splash

import com.fitnest.domain.usecase.splash.GenerateTokenUseCase
import com.fitnest.presentation.base.BaseViewModel
import com.fitnest.presentation.navigation.Route
import kotlinx.coroutines.launch

class SplashViewModel(
    private val generateTokenUseCase: GenerateTokenUseCase
) : BaseViewModel() {

    internal fun generateToken() {
        viewModelScope.launch(exceptionHandler) {
            handleProgress(true)
            generateTokenUseCase()
            handleProgress()
        }
    }

    internal fun navigateNext() {
        handleRoute(Route.Proxy())
    }
}
