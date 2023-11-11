package com.fitnest.presentation.decompose.onboarding

import com.arkivanov.decompose.ComponentContext
import com.fitnest.domain.usecase.onboarding.SubmitAndGetNextOnboardingStepUseCase
import com.fitnest.presentation.navigation.Route
import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.bindProvider
import org.kodein.di.instance

val onboardingAreaDIModule by lazy {
    DI.Module("onboardingAreaDIModule") {
        bindFactory<OnboardingAreaComponentDIParams, OnboardingAreaComponent> {
            val context = it.componentContext
            val initialStep = it.initialStep
            DefaultOnboardingAreaComponent(context, initialStep, instance())
        }
        bindProvider { SubmitAndGetNextOnboardingStepUseCase(instance(), instance(), instance()) }
    }
}

class OnboardingAreaComponentDIParams(
    val componentContext: ComponentContext,
    val initialStep: String,
    val onNavigate: (Route) -> Unit
)
