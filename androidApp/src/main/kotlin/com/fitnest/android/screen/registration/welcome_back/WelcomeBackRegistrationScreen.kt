package com.fitnest.android.screen.registration.welcome_back

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitnest.android.di.RegistrationModule
import com.fitnest.presentation.navigation.Route
import com.fitnest.presentation.screen.registration.welcome_back.WelcomeBackRegistrationViewModel
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.subDI

@Composable
internal fun WelcomeBackRegistrationScreen(onNavigate: (Route) -> Unit) = subDI(diBuilder = {
    import(RegistrationModule.welcomeBackRegistrationScreenModule)
}) {
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()
    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = WelcomeBackRegistrationViewModel::class.java
    )

    com.fitnest.presentation.screen.registration.welcome_back.WelcomeBackRegistrationScreen(
        viewModel = viewModel,
        onNavigate = onNavigate
    )
}