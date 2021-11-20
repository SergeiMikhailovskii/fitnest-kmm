package com.fitnest.android.view.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.rememberNavController
import com.fitnest.android.base.Route
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.kodein.di.compose.rememberInstance

@Preview
@Composable
fun OnboardingProxyScreenPreview() {
    OnboardingProxyScreen(navController = rememberNavController(ComposeNavigator()))
}

@Composable
fun OnboardingProxyScreen(navController: NavController) {
    val viewModel: OnboardingViewModel by rememberInstance()

    LaunchedEffect(null) {
        launch {
            viewModel.routeSharedFlow.collect {
                handleNavigation(
                    route = it,
                    navController = navController
                )
            }
        }
        viewModel.getOnboardingStep()
    }

    Scaffold {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}