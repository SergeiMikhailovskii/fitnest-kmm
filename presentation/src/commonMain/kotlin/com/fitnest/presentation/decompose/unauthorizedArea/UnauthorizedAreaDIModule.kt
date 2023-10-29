package com.fitnest.presentation.decompose.unauthorizedArea

import com.arkivanov.decompose.ComponentContext
import com.fitnest.presentation.decompose.unauthorizedArea.splash.splashDIModule
import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.instance

val unauthorizedAreaDIModule by lazy {
    DI.Module {
        bindFactory<ComponentContext, UnauthorizedAreaComponent> {
            DefaultUnauthorizedAreaComponent(it, instance())
        }
        import(splashDIModule)
    }
}
