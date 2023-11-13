package com.fitnest.presentation.decompose.registration

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.fitnest.presentation.decompose.registration.steps.createAccount.CreateAccountRegistrationView

@Composable
fun RegistrationAreaView(component: RegistrationAreaComponent) {
    Children(component.childStack) {
        when (val child = it.instance) {
            is RegistrationAreaComponent.Child.CreateAccount -> CreateAccountRegistrationView(child.component)
        }
    }
}
