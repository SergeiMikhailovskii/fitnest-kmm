package com.fitnest.presentation.decompose.unauthorizedArea

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.fitnest.presentation.decompose.unauthorizedArea.splash.DefaultSplashComponent

class DefaultUnauthorizedAreaComponent(
    context: ComponentContext
) : UnauthorizedAreaComponent, ComponentContext by context {
    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, UnauthorizedAreaComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.Unauthorized,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(config: Config, context: ComponentContext) = when (config) {
        Config.Unauthorized -> UnauthorizedAreaComponent.Child.SplashChild(DefaultSplashComponent())
    }

    private sealed interface Config : Parcelable {
        @Parcelize
        data object Unauthorized : Config
    }
}
