package com.fitnest.presentation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.fitnest.presentation.decompose.RootComponent
import com.fitnest.presentation.decompose.onboarding.OnboardingAreaView
import com.fitnest.presentation.decompose.proxy.ProxyView
import com.fitnest.presentation.decompose.splash.SplashView
import com.fitnest.presentation.style.FitnestTheme

@Composable
fun FitnestApp(component: RootComponent) {
    FitnestTheme {
        Children(component.childStack) {
            when (val child = it.instance) {
                is RootComponent.Child.Splash -> SplashView(child.component)
                is RootComponent.Child.Proxy -> ProxyView(child.component)
                is RootComponent.Child.Onboarding -> OnboardingAreaView(child.component)
            }
        }
    }
}
