package com.fitnest.presentation.decompose.registration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.fitnest.presentation.decompose.registration.steps.completeAccount.DefaultCompleteAccountRegistrationComponent
import com.fitnest.presentation.decompose.registration.steps.createAccount.CreateAccountRegistrationComponent
import com.fitnest.presentation.decompose.registration.steps.createAccount.CreateAccountRegistrationComponentDIParams
import com.fitnest.presentation.navigation.Route
import org.kodein.di.DI
import org.kodein.di.instance

class DefaultRegistrationAreaComponent(
    context: ComponentContext,
    initialStep: String,
    private val di: DI,
    private val onNavigate: (Route) -> Unit
) : RegistrationAreaComponent, ComponentContext by context {

    private val navigation = StackNavigation<Config>()

    override val childStack = childStack(
        source = navigation,
        initialConfiguration = getStepByName(initialStep),
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(config: Config, context: ComponentContext): RegistrationAreaComponent.Child =
        when (config) {
            Config.CreateAccount -> {
                val component by di.instance<CreateAccountRegistrationComponentDIParams, CreateAccountRegistrationComponent> {
                    CreateAccountRegistrationComponentDIParams(context, ::handle)
                }
                RegistrationAreaComponent.Child.CreateAccount(component)
            }

            Config.CompleteAccount -> RegistrationAreaComponent.Child.CompleteAccount(
                DefaultCompleteAccountRegistrationComponent(context)
            )

            Config.Goal -> TODO()
            Config.WelcomeBack -> TODO()
        }

    private fun getStepByName(stepName: String) = when (stepName) {
        "STEP_CREATE_ACCOUNT" -> Config.CreateAccount
        "STEP_COMPLETE_ACCOUNT" -> Config.CompleteAccount
        "STEP_GOAL" -> Config.Goal
        "STEP_WELCOME_BACK" -> Config.WelcomeBack
        else -> error("Unknown step - $stepName")
    }

    private fun handle(route: Route) {
        if (route is Route.Registration2.Step) {
            val config = getStepByName(route.stepName)
            navigation.replaceAll(config)
        } else {
            onNavigate(route)
        }
    }

    sealed interface Config : Parcelable {
        @Parcelize
        data object CreateAccount : Config

        @Parcelize
        data object CompleteAccount : Config

        @Parcelize
        data object Goal : Config

        @Parcelize
        data object WelcomeBack : Config
    }
}
