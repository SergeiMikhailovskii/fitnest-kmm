package com.fitnest.presentation.decompose.unauthorizedArea.splash

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.fitnest.domain.usecase.splash.GenerateTokenUseCase
import com.fitnest.presentation.extension.disposableScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DefaultSplashComponent(
    context: ComponentContext,
    private val generateTokenUseCase: GenerateTokenUseCase
) : SplashComponent, CoroutineScope by context.disposableScope() {

    private val _model = MutableValue(SplashComponent.Model())
    override val model: Value<SplashComponent.Model> = _model

    override fun onButtonNextClicked() {
        launch {
            _model.value = _model.value.copy(isLoading = true)
            generateTokenUseCase().getOrThrow()
            _model.value = _model.value.copy(isLoading = false)
        }
    }
}
