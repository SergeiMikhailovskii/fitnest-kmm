package com.fitnest.android.screen.private_area.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitnest.android.screen.private_area.home.composable.*
import com.fitnest.android.style.Padding
import org.kodein.di.compose.rememberInstance
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Preview
@Composable
fun HomeScreen() {
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()
    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = HomeViewModel::class.java
    )

    val screenData by viewModel.screenDataFlow.collectAsState()
    val loading by viewModel.progressSharedFlow.collectAsState(true)

    Scaffold {
        if (loading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(horizontal = Padding.Padding30)
                    .padding(bottom = Padding.Padding30)
                    .verticalScroll(rememberScrollState())
            ) {
                screenData.headerWidget?.let { HeaderBlock(it) }
                screenData.bmiWidget?.let { BMIBlock(it) }
                screenData.todayTargetWidget?.let { TodayTargetBlock(it) }
                screenData.activityStatusWidget?.let { ActivityStatusBlock(it) }
                screenData.latestWorkoutWidget?.let { LatestWorkoutBlock(it) }
                Box(modifier = Modifier.height(200.dp))
            }
        }
    }
}
