package com.fitnest.presentation.decompose.splash

import com.arkivanov.decompose.ComponentContext
import com.fitnest.domain.usecase.splash.GenerateTokenUseCase
import com.fitnest.presentation.navigation.Route
import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.bindProvider
import org.kodein.di.instance

val splashDIModule by lazy {
    DI.Module("splashDIModule") {
        bindFactory<SplashComponentDIParams, SplashComponent> {
            val context = it.componentContext
            val onNavigate = it.onNavigate
            DefaultSplashComponent(context, instance(), onNavigate)
        }
        bindProvider { GenerateTokenUseCase(instance(), instance()) }
    }
}

class SplashComponentDIParams(
    val componentContext: ComponentContext,
    val onNavigate: (Route) -> Unit
)
