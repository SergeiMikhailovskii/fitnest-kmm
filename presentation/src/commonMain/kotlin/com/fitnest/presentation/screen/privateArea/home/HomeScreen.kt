package com.fitnest.presentation.screen.privateArea.home

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
import com.fitnest.presentation.internal.ErrorHandlerDelegate
import com.fitnest.presentation.navigation.Route
import com.fitnest.presentation.screen.privateArea.home.composable.ActivityStatusBlock
import com.fitnest.presentation.screen.privateArea.home.composable.BMIBlock
import com.fitnest.presentation.screen.privateArea.home.composable.HeaderBlock
import com.fitnest.presentation.screen.privateArea.home.composable.LatestWorkoutBlock
import com.fitnest.presentation.screen.privateArea.home.composable.TodayTargetBlock
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import kotlinx.coroutines.launch
import org.kodein.di.compose.rememberInstance

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigate: (Route) -> Unit
) {
    val errorHandlerDelegate: ErrorHandlerDelegate by rememberInstance()
    val screenData by viewModel.screenDataFlow.collectAsState()
    val loading by viewModel.progressSharedFlow.collectAsState(false)

    LaunchedEffect(null) {
        launch { viewModel.routeSharedFlow.collect(onNavigate) }
        launch { viewModel.failureSharedFlow.collect(errorHandlerDelegate::defaultHandleFailure) }
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
