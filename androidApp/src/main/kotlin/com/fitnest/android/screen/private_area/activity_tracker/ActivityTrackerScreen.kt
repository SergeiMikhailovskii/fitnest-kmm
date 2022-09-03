package com.fitnest.android.screen.private_area.activity_tracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitnest.android.screen.private_area.activity_tracker.composable.ActivityProgressBlock
import com.fitnest.android.screen.private_area.activity_tracker.composable.LatestActivityBlock
import com.fitnest.android.screen.private_area.activity_tracker.composable.TodayTargetBlock
import com.fitnest.android.style.Padding
import org.kodein.di.compose.rememberInstance

@Preview
@Composable
internal fun ActivityTrackerScreen() {
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()

    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = ActivityTrackerViewModel::class.java
    )

    val screenData by viewModel.screenDataFlow.collectAsState()

    Scaffold {
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
                    data = it
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
                    activities = it.activities
                )
            }
        }
    }
}
