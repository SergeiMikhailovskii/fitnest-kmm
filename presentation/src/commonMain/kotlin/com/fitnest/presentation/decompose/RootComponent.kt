package com.fitnest.presentation.decompose

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.fitnest.presentation.decompose.onboarding.OnboardingAreaComponent
import com.fitnest.presentation.decompose.proxy.ProxyComponent
import com.fitnest.presentation.decompose.splash.SplashComponent

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class Splash(val component: SplashComponent) : Child
        data class Proxy(val component: ProxyComponent) : Child
        data class Onboarding(val component: OnboardingAreaComponent) : Child
    }
}
