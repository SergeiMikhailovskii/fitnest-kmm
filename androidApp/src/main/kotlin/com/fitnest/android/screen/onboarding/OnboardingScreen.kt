package com.fitnest.android.screen.onboarding

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitnest.android.di.onboardingModule
import com.fitnest.presentation.navigation.Route
import com.fitnest.presentation.screen.onboarding.OnboardingViewModel
import org.kodein.di.compose.localDI
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.subDI

@ExperimentalMaterial3Api
@Composable
fun OnboardingScreen(stepName: String, navigate: (Route) -> Unit) = subDI(diBuilder = {
    import(module = onboardingModule, allowOverride = true)
}) {
    val localDi = localDI()
    val viewModelFactory: OnboardingViewModelFactory by rememberInstance {
        OnboardingViewModelFactory.Params(stepName, localDi)
    }

    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = OnboardingViewModel::class.java
    )

    com.fitnest.presentation.screen.onboarding.OnboardingScreen(
        viewModel = viewModel,
        onNavigate = navigate
    )
}
