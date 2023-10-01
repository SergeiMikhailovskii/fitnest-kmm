package com.fitnest.presentation.decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.fitnest.presentation.decompose.unauthorizedArea.DefaultUnauthorizedAreaComponent

class DefaultRootComponent(
    context: ComponentContext
) : RootComponent, ComponentContext by context {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.Unauthorized,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(config: Config, context: ComponentContext) = when (config) {
        Config.Unauthorized -> RootComponent.Child.UnauthorizedChild(DefaultUnauthorizedAreaComponent(context))
    }

    private sealed interface Config : Parcelable {
        @Parcelize
        data object Unauthorized : Config
    }
}
