package com.fitnest.presentation.decompose.registration

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.fitnest.presentation.decompose.registration.steps.completeAccount.CompleteAccountRegistrationComponent
import com.fitnest.presentation.decompose.registration.steps.createAccount.CreateAccountRegistrationComponent

interface RegistrationAreaComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class CreateAccount(val component: CreateAccountRegistrationComponent) : Child
        class CompleteAccount(val component: CompleteAccountRegistrationComponent) : Child
    }
}
