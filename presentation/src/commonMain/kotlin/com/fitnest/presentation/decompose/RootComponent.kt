package com.fitnest.presentation.decompose

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.fitnest.presentation.decompose.unauthorizedArea.UnauthorizedAreaComponent

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class UnauthorizedChild(val component: UnauthorizedAreaComponent) : Child
    }
}
