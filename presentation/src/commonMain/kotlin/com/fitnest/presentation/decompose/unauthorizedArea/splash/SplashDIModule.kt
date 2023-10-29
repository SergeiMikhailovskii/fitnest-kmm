package com.fitnest.presentation.decompose.unauthorizedArea.splash

import com.arkivanov.decompose.ComponentContext
import com.fitnest.domain.usecase.splash.GenerateTokenUseCase
import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.bindProvider
import org.kodein.di.instance

val splashDIModule by lazy {
    DI.Module {
        bindFactory<ComponentContext, SplashComponent> {
            DefaultSplashComponent(it, instance())
        }
        bindProvider { GenerateTokenUseCase(instance(), instance()) }
    }
}
