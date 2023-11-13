package com.fitnest.presentation.decompose.registration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.fitnest.domain.usecase.registration.SubmitRegistrationStepAndGetNextUseCase
import com.fitnest.presentation.navigation.Route

class DefaultRegistrationAreaComponent(
    context: ComponentContext,
    initialStep: String,
    private val submitRegistrationStepUseCase: SubmitRegistrationStepAndGetNextUseCase,
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
            Config.CreateAccount -> TODO()
            Config.CompleteAccount -> TODO()
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
