package com.fitnest.android.screen.splash

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitnest.android.di.splashModule
import com.fitnest.presentation.navigation.Route
import com.fitnest.presentation.screen.splash.SplashViewModel
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.subDI

@Composable
internal fun SplashScreen(navigate: (Route) -> Unit) = subDI(diBuilder = { import(splashModule) }) {
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()

    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = SplashViewModel::class.java
    )

    com.fitnest.presentation.screen.splash.SplashScreen(viewModel = viewModel, navigate = navigate)
}
