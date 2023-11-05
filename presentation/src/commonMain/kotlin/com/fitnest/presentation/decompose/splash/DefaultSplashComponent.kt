package com.fitnest.presentation.decompose.splash

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.fitnest.domain.usecase.splash.GenerateTokenUseCase
import com.fitnest.presentation.extension.disposableScope
import com.fitnest.presentation.navigation.Route
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DefaultSplashComponent(
    context: ComponentContext,
    private val generateTokenUseCase: GenerateTokenUseCase,
    private val onNavigate: (Route) -> Unit
) : SplashComponent, CoroutineScope by context.disposableScope() {

    private val _model = MutableValue(SplashComponent.Model())
    override val model: Value<SplashComponent.Model> = _model

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Napier.e(throwable.message.orEmpty(), throwable)
    }

    init {
        launch(exceptionHandler) {
            _model.value = _model.value.copy(isLoading = true)
            generateTokenUseCase()
            _model.value = _model.value.copy(isLoading = false)
        }
    }

    override fun onButtonNextClicked() {
        onNavigate(Route.Proxy())
    }
}
