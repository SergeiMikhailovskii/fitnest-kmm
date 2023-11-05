package com.fitnest.presentation.decompose.onboarding

import com.arkivanov.decompose.ComponentContext
import com.fitnest.presentation.navigation.Route
import org.kodein.di.DI
import org.kodein.di.bindFactory

val onboardingAreaDIModule by lazy {
    DI.Module("onboardingAreaDIModule") {
        bindFactory<OnboardingAreaComponentDIParams, OnboardingAreaComponent> {
            DefaultOnboardingAreaComponent()
        }
    }
}

class OnboardingAreaComponentDIParams(
    val componentContext: ComponentContext,
    val onNavigate: (Route) -> Unit
)
