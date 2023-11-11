package com.fitnest.presentation.decompose.onboarding

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.fitnest.presentation.decompose.onboarding.page.OnboardingPageComponent

interface OnboardingAreaComponent {
    val childStack: Value<ChildStack<*, Page>>

    data class Page(val component: OnboardingPageComponent)
}
