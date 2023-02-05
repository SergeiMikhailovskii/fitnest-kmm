package com.fitnest.android.screen.private_area.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fitnest.android.di.PrivateAreaModule
import com.fitnest.android.internal.ErrorHandlerDelegate
import com.fitnest.android.navigation.handleNavigation
import com.fitnest.android.screen.private_area.home.composable.ActivityStatusBlock
import com.fitnest.android.screen.private_area.home.composable.BMIBlock
import com.fitnest.android.screen.private_area.home.composable.HeaderBlock
import com.fitnest.android.screen.private_area.home.composable.LatestWorkoutBlock
import com.fitnest.android.screen.private_area.home.composable.TodayTargetBlock
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Padding
import com.google.accompanist.navigation.animation.AnimatedComposeNavigator
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.launch
import org.kodein.di.compose.localDI
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.subDI
import kotlin.time.ExperimentalTime

@Preview
@Composable
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalTime
fun HomeScreenPreview() {
    HomeScreen(navController = rememberAnimatedNavController(AnimatedComposeNavigator()))
}

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(navController: NavController) = subDI(diBuilder = {
    import(PrivateAreaModule.dashboardPrivateAreaModule)
}) {
    val di = localDI()
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance { di }
    val errorHandlerDelegate: ErrorHandlerDelegate by rememberInstance()

    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = HomeViewModel::class.java
    )

    val screenData by viewModel.screenDataFlow.collectAsState()
    val loading by viewModel.progressSharedFlow.collectAsState(false)

    LaunchedEffect(null) {
        launch {
            viewModel.routeSharedFlow.collect {
                handleNavigation(route = it, navController = navController)
            }
        }
        launch {
            viewModel.failureSharedFlow.collect(errorHandlerDelegate::defaultHandleFailure)
        }
    }

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = Padding.Padding30)
                .padding(bottom = Padding.Padding30)
                .verticalScroll(rememberScrollState())
        ) {
            screenData.headerWidget?.let { HeaderBlock(it, viewModel, loading) }
            screenData.bmiWidget?.let { BMIBlock(it, loading) }
            screenData.todayTargetWidget?.let { TodayTargetBlock(viewModel, loading) }
            screenData.activityStatusWidget?.let { ActivityStatusBlock(it) }
            screenData.latestWorkoutWidget?.let { LatestWorkoutBlock(it) }
            Box(modifier = Modifier.height(Dimen.Dimen200))
        }
    }
}
