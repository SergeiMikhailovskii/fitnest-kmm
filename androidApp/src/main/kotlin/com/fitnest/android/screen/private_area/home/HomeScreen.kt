package com.fitnest.android.screen.private_area.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitnest.android.di.PrivateAreaModule
import com.fitnest.presentation.navigation.Route
import com.fitnest.presentation.screen.privateArea.home.HomeViewModel
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.subDI
import kotlin.time.ExperimentalTime

@Preview
@Composable
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalTime
fun HomeScreenPreview() {
    HomeScreen {}
}

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(onNavigate: (Route) -> Unit) = subDI(diBuilder = {
    import(PrivateAreaModule.dashboardPrivateAreaModule)
}) {
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()

    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = HomeViewModel::class.java
    )

    com.fitnest.presentation.screen.privateArea.home.HomeScreen(viewModel = viewModel, onNavigate = onNavigate)
}
