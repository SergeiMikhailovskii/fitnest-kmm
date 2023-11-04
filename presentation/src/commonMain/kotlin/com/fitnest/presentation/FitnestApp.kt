package com.fitnest.presentation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.fitnest.presentation.decompose.RootComponent
import com.fitnest.presentation.decompose.unauthorizedArea.UnauthorizedAreaView
import com.fitnest.presentation.style.FitnestTheme

@Composable
fun FitnestApp(component: RootComponent) {
    FitnestTheme {
        Children(component.childStack) {
            when (val child = it.instance) {
                is RootComponent.Child.UnauthorizedChild -> UnauthorizedAreaView(child.component)
            }
        }
    }
}
