package com.fitnest.android.screen.private_area.activity_tracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fitnest.android.di.PrivateAreaModule
import com.fitnest.android.screen.private_area.activity_tracker.composable.ActivityProgressBlock
import com.fitnest.android.screen.private_area.activity_tracker.composable.LatestActivityBlock
import com.fitnest.android.screen.private_area.activity_tracker.composable.TodayTargetBlock
import com.fitnest.presentation.internal.ErrorHandlerDelegate
import com.fitnest.presentation.screen.privateArea.activityTracker.ActivityTrackerViewMapper
import com.fitnest.presentation.screen.privateArea.activityTracker.ActivityTrackerViewModel
import com.fitnest.presentation.style.Padding
import kotlinx.coroutines.launch
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.subDI

@Composable
internal fun ActivityTrackerScreen(
    navController: NavController,
    navigate: (com.fitnest.presentation.navigation.Route) -> Unit
) = subDI(diBuilder = {
    import(PrivateAreaModule.activityTrackerPrivateAreaModule)
}) {
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()
    val errorHandlerDelegate: ErrorHandlerDelegate by rememberInstance()
    val viewMapper: ActivityTrackerViewMapper by rememberInstance()

    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = ActivityTrackerViewModel::class.java
    )

    val screenData by viewModel.screenDataFlow.collectAsState()
    val progress by viewModel.progressSharedFlow.collectAsState(false)

    LaunchedEffect(null) {
        launch {
            viewModel.failureSharedFlow.collect(errorHandlerDelegate::defaultHandleFailure)
        }
        launch {
            viewModel.routeSharedFlow.collect { navigate(it) }
        }
    }
    LaunchedEffect(key1 = navController.currentBackStackEntry) {
        viewModel.getInitialInfo()
    }
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = Padding.Padding20)
    ) {
        screenData.todayTargetWidget?.let {
            TodayTargetBlock(
                modifier = Modifier.padding(
                    start = Padding.Padding30,
                    end = Padding.Padding30,
                    top = Padding.Padding24
                ),
                data = it,
                onAddActivityClicked = viewModel::openActivityInputBottomSheet,
                progress = progress
            )
        }
        screenData.activityProgressWidget?.let {
            val progresses = viewMapper.mapActivityProgressesColors(it.progresses)
            ActivityProgressBlock(
                modifier = Modifier.padding(
                    top = Padding.Padding30,
                    start = Padding.Padding30,
                    end = Padding.Padding30
                ),
                sections = progresses,
                progress = progress
            )
        }
        screenData.latestActivityWidget?.let {
            LatestActivityBlock(
                modifier = Modifier.padding(Padding.Padding30),
                viewModel = viewModel,
                activities = it.activities
            )
        }
    }
}
