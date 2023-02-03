package com.fitnest.android.screen.splash

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.domain.usecase.splash.GenerateTokenUseCase
import kotlinx.coroutines.launch

internal class SplashViewModel(
    private val generateTokenUseCase: GenerateTokenUseCase
) : BaseViewModel() {

    internal fun generateToken() {
        viewModelScope.launch(exceptionHandler) {
            handleProgress(true)
            generateTokenUseCase().getOrThrow()
            handleProgress()
        }
    }

    internal fun navigateNext() {
        handleRoute(Route.Proxy())
    }
}
