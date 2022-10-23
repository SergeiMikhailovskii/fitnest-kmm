package com.fitnest.android.screen.splash

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.domain.enum.FlowType
import com.fitnest.domain.usecase.GenerateTokenUseCase
import kotlinx.coroutines.launch

internal class SplashViewModel(
    private val generateTokenUseCase: GenerateTokenUseCase
) : BaseViewModel() {

    private var redirectFlow: FlowType? = null

    internal fun generateToken() {
        viewModelScope.launch(exceptionHandler) {
            handleProgress(true)
            val result = generateTokenUseCase().getOrThrow()
            redirectFlow = result.getFlow()
            handleProgress()
        }
    }

    internal fun navigateNext() {
        handleRoute(Route.Proxy())
    }
}
