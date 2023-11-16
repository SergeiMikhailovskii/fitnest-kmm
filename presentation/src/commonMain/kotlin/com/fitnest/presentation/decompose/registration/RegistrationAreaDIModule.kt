package com.fitnest.presentation.decompose.registration

import com.arkivanov.decompose.ComponentContext
import com.fitnest.domain.usecase.registration.SubmitRegistrationStepAndGetNextUseCase
import com.fitnest.presentation.decompose.registration.steps.createAccount.createAccountRegistrationDIModule
import com.fitnest.presentation.navigation.Route
import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.bindProvider
import org.kodein.di.instance

val registrationAreaDIModule by lazy {
    DI.Module("registrationAreaDIModule") {
        bindFactory<RegistrationAreaComponentDIParams, RegistrationAreaComponent> {
            val context = it.componentContext
            val initialStep = it.initialStep
            val onNavigate = it.onNavigate
            DefaultRegistrationAreaComponent(context, initialStep, di, onNavigate)
        }
        bindProvider {
            SubmitRegistrationStepAndGetNextUseCase(
                instance(),
                instance(),
                instance(),
                instance()
            )
        }
        import(createAccountRegistrationDIModule)
    }
}

class RegistrationAreaComponentDIParams(
    val componentContext: ComponentContext,
    val initialStep: String,
    val onNavigate: (Route) -> Unit
)
