package com.fitnest.android.screen.private_area.activity_tracker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitnest.android.internal.ErrorHandlerDelegate
import com.fitnest.android.screen.private_area.activity_tracker.composable.ActivityInputBottomSheet
import com.fitnest.android.screen.private_area.activity_tracker.composable.ActivityProgressBlock
import com.fitnest.android.screen.private_area.activity_tracker.composable.LatestActivityBlock
import com.fitnest.android.screen.private_area.activity_tracker.composable.TodayTargetBlock
import com.fitnest.android.style.Padding
import kotlinx.coroutines.launch
import org.kodein.di.compose.rememberInstance

@ExperimentalMaterialApi
@Preview
@Composable
internal fun ActivityTrackerScreen() {
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()
    val errorHandlerDelegate: ErrorHandlerDelegate by rememberInstance()

    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = ActivityTrackerViewModel::class.java
    )

    val screenData by viewModel.screenDataFlow.collectAsState()
    val progress by viewModel.progressStateFlow.collectAsState()

    val modalBottomSheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden
    )
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(null) {
        launch {
            viewModel.failureSharedFlow.collect(errorHandlerDelegate::defaultHandleFailure)
        }
    }

    ModalBottomSheetLayout(
        sheetContent = {
            Box(modifier = Modifier.fillMaxSize()) {
                ActivityInputBottomSheet { activityType, value ->
                    coroutineScope.launch {
                        modalBottomSheetState.hide()
                        viewModel.saveActivity(activityType, value)
                    }
                }
            }
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetState = modalBottomSheetState
    ) {
        if (progress) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
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
                        onAddActivityClicked = {
                            coroutineScope.launch {
                                modalBottomSheetState.show()
                            }
                        }
                    )
                }
                screenData.activityProgressWidget?.let {
                    ActivityProgressBlock(
                        modifier = Modifier.padding(
                            top = Padding.Padding30,
                            start = Padding.Padding30,
                            end = Padding.Padding30
                        ),
                        sections = it.progresses
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
    }
}
