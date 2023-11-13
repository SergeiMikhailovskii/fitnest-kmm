package com.fitnest.presentation.decompose.registration

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface RegistrationAreaComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child
}
