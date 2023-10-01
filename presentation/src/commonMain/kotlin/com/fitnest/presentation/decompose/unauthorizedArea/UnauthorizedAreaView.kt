package com.fitnest.presentation.decompose.unauthorizedArea

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.fitnest.presentation.decompose.unauthorizedArea.splash.SplashView

@Composable
fun UnauthorizedAreaView(component: UnauthorizedAreaComponent) {
    Children(component.childStack) {
        when (val child = it.instance) {
            is UnauthorizedAreaComponent.Child.SplashChild -> SplashView()
        }
    }
}
