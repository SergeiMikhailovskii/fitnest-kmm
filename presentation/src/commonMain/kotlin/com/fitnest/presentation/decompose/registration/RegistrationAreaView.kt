package com.fitnest.presentation.decompose.registration

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.fitnest.presentation.decompose.registration.steps.completeAccount.CompleteAccountRegistrationView
import com.fitnest.presentation.decompose.registration.steps.createAccount.CreateAccountRegistrationView

@Composable
fun RegistrationAreaView(component: RegistrationAreaComponent) {
    Children(
        component.childStack,
        animation = stackAnimation(slide())
    ) {
        when (val child = it.instance) {
            is RegistrationAreaComponent.Child.CreateAccount -> CreateAccountRegistrationView(child.component)
            is RegistrationAreaComponent.Child.CompleteAccount -> CompleteAccountRegistrationView(child.component)
        }
    }
}
