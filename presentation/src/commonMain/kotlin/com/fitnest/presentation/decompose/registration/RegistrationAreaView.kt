package com.fitnest.presentation.decompose.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children

@Composable
fun RegistrationAreaView(component: RegistrationAreaComponent) {
    Children(component.childStack) {
        when (val child = it.instance) {
            else -> Column { }
        }
    }
}
