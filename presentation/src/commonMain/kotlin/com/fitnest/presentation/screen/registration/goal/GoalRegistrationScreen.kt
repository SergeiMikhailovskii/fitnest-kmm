package com.fitnest.presentation.screen.registration.goal

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.fitnest.presentation.MR
import com.fitnest.presentation.internal.ErrorHandlerDelegate
import com.fitnest.presentation.navigation.Route
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.kodein.di.compose.rememberInstance

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GoalRegistrationScreen(
    viewModel: GoalRegistrationViewModel,
    onNavigate: (Route) -> Unit,
    pager: @Composable ColumnScope.() -> Unit
) {
    val viewMapper: GoalRegistrationViewMapper by rememberInstance()
    val errorHandlerDelegate: ErrorHandlerDelegate by rememberInstance()

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { 3 }
    )

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collectLatest { page ->
            val goalType = viewMapper.mapGoalIndexToGoalType(page)
            viewModel.setGoal(goalType)
        }
    }

    LaunchedEffect(null) {
        launch {
            viewModel.routeSharedFlow.collect(onNavigate)
        }
        launch {
            viewModel.failureSharedFlow.collect(errorHandlerDelegate::defaultHandleFailure)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(MR.strings.registration_goal_title),
            modifier = Modifier.padding(top = Padding.Padding40),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            stringResource(MR.strings.registration_goal_description),
            modifier = Modifier.padding(top = Padding.Padding5),
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )

        pager()

        Button(
            onClick = viewModel::submitRegistration,
            shape = CircleShape,
            modifier = Modifier
                .padding(
                    start = Padding.Padding30,
                    end = Padding.Padding30,
                    bottom = Padding.Padding40
                )
                .height(Dimen.Dimen60)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(MR.strings.registration_goal_next_button_label),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}
