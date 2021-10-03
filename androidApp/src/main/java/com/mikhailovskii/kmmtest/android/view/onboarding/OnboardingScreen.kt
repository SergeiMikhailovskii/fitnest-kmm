package com.mikhailovskii.kmmtest.android.view.onboarding

import androidx.compose.foundation.Image
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.mikhailovskii.kmmtest.android.R
import com.mikhailovskii.kmmtest.entity.OnboardingState
import org.kodein.di.compose.instance
import org.kodein.di.compose.withDI

@Composable
fun OnboardingScreen(navController: NavController, type: String) = withDI {
    val viewModel: OnboardingViewModel by instance()
    val screenState: OnboardingState? by viewModel.stateLiveData.observeAsState(null)

    LaunchedEffect(key1 = null) {
        viewModel.updateScreenState(type)
    }

    Scaffold {
        Image(
            painterResource(id = screenState?.imageResId ?: R.drawable.ic_onboarding_first),
            contentDescription = null
        )
    }
}