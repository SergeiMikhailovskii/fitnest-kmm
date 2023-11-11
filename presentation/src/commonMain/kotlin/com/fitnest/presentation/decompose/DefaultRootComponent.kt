package com.fitnest.presentation.decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.fitnest.di.dataExceptionHandlerModule
import com.fitnest.di.repositoryModule
import com.fitnest.di.serializationModule
import com.fitnest.di.serviceModule
import com.fitnest.domain.di.mapperModule
import com.fitnest.domain.enum.FlowType
import com.fitnest.presentation.decompose.onboarding.OnboardingAreaComponent
import com.fitnest.presentation.decompose.onboarding.OnboardingAreaComponentDIParams
import com.fitnest.presentation.decompose.onboarding.onboardingAreaDIModule
import com.fitnest.presentation.decompose.proxy.ProxyComponent
import com.fitnest.presentation.decompose.proxy.ProxyComponentDIParams
import com.fitnest.presentation.decompose.proxy.proxyDIModule
import com.fitnest.presentation.decompose.registration.registrationDIModule
import com.fitnest.presentation.decompose.splash.SplashComponent
import com.fitnest.presentation.decompose.splash.SplashComponentDIParams
import com.fitnest.presentation.decompose.splash.splashDIModule
import com.fitnest.presentation.navigation.Route
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

class DefaultRootComponent(
    context: ComponentContext
) : RootComponent, ComponentContext by context, DIAware {

    private val navigation = StackNavigation<Config>()

    override val di by DI.lazy {
        import(dataExceptionHandlerModule)
        import(serviceModule)
        import(serializationModule)
        import(mapperModule)
        import(repositoryModule)
        import(splashDIModule)
        import(proxyDIModule)
        import(registrationDIModule)
        import(onboardingAreaDIModule)
    }

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.Splash,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(config: Config, context: ComponentContext) = when (config) {
        Config.Splash -> {
            val component by di.instance<SplashComponentDIParams, SplashComponent> {
                SplashComponentDIParams(context, ::handle)
            }
            RootComponent.Child.Splash(component)
        }

        is Config.Proxy -> {
            val component by di.instance<ProxyComponentDIParams, ProxyComponent> {
                ProxyComponentDIParams(context, FlowType.UNKNOWN, ::handle)
            }
            RootComponent.Child.Proxy(component)
        }

        is Config.Onboarding -> {
            val component by di.instance<OnboardingAreaComponentDIParams, OnboardingAreaComponent> {
                OnboardingAreaComponentDIParams(context, config.initialStep, ::handle)
            }
            RootComponent.Child.Onboarding(component)
        }
    }

    private fun handle(route: Route) {
        if (route is Route.Proxy) {
            navigation.replaceAll(Config.Proxy(route.flow))
        } else if (route is Route.Onboarding) {
            navigation.replaceAll(Config.Onboarding(route.initialStep))
        }
    }

    private sealed interface Config : Parcelable {
        @Parcelize
        data object Splash : Config

        @Parcelize
        data class Proxy(val flowType: FlowType) : Config

        @Parcelize
        data class Onboarding(val initialStep: String) : Config
    }
}
