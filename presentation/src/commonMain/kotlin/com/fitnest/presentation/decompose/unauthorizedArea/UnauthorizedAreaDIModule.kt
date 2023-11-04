package com.fitnest.presentation.decompose.unauthorizedArea

import com.arkivanov.decompose.ComponentContext
import com.fitnest.presentation.decompose.unauthorizedArea.splash.splashDIModule
import org.kodein.di.DI
import org.kodein.di.bindFactory

val unauthorizedAreaDIModule by lazy {
    DI.Module("unauthorizedAreaDIModule") {
        bindFactory<ComponentContext, UnauthorizedAreaComponent> {
            DefaultUnauthorizedAreaComponent(it, di)
        }
        import(splashDIModule)
    }
}
