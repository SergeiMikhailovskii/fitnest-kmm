package com.fitnest.presentation.decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.fitnest.di.dataExceptionHandlerModule
import com.fitnest.di.repositoryModule
import com.fitnest.di.serviceModule
import com.fitnest.presentation.decompose.unauthorizedArea.UnauthorizedAreaComponent
import com.fitnest.presentation.decompose.unauthorizedArea.unauthorizedAreaDIModule
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

class DefaultRootComponent(
    context: ComponentContext
) : RootComponent, ComponentContext by context, DIAware {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.Unauthorized,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(config: Config, context: ComponentContext) = when (config) {
        Config.Unauthorized -> {
            val component by di.instance<UnauthorizedAreaComponent> { context }
            RootComponent.Child.UnauthorizedChild(component)
        }
    }

    private sealed interface Config : Parcelable {
        @Parcelize
        data object Unauthorized : Config
    }

    override val di by DI.lazy {
        import(dataExceptionHandlerModule)
        import(serviceModule)
        import(repositoryModule)
        import(unauthorizedAreaDIModule)
    }
}
