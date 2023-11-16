package com.fitnest.presentation.decompose.registration.steps.createAccount

import com.arkivanov.decompose.ComponentContext
import com.fitnest.domain.usecase.validation.CreateAccountRegistrationValidationUseCase
import com.fitnest.presentation.navigation.Route
import com.fitnest.presentation.screen.registration.createAccount.CreateAccountRegistrationViewMapper
import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.bindProvider
import org.kodein.di.instance

val createAccountRegistrationDIModule by lazy {
    DI.Module("createAccountRegistrationDIModule") {
        bindFactory<CreateAccountRegistrationComponentDIParams, CreateAccountRegistrationComponent> {
            val context = it.componentContext
            val onNavigate = it.onNavigate
            DefaultCreateAccountRegistrationComponent(
                context,
                instance(),
                instance(),
                instance(),
                instance(),
                onNavigate
            )
        }
        bindProvider { CreateAccountRegistrationViewMapper() }
        bindProvider { CreateAccountRegistrationValidationUseCase() }
    }
}

class CreateAccountRegistrationComponentDIParams(
    val componentContext: ComponentContext,
    val onNavigate: (Route) -> Unit
)
