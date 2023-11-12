package com.fitnest.presentation.decompose.registration

import com.arkivanov.decompose.ComponentContext
import com.fitnest.presentation.navigation.Route
import org.kodein.di.DI
import org.kodein.di.bindFactory

val registrationAreaDIModule by lazy {
    DI.Module("registrationAreaDIModule") {
        bindFactory<RegistrationAreaComponentDIParams, RegistrationAreaComponent> {
            val context = it.componentContext
            val initialStep = it.initialStep
            val onNavigate = it.onNavigate
            DefaultRegistrationAreaComponent()
        }
    }
}

class RegistrationAreaComponentDIParams(
    val componentContext: ComponentContext,
    val initialStep: String,
    val onNavigate: (Route) -> Unit
)
