package com.fitnest.presentation.decompose.unauthorizedArea

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.fitnest.presentation.decompose.unauthorizedArea.splash.SplashComponent

interface UnauthorizedAreaComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class SplashChild(val component: SplashComponent) : Child
    }
}
