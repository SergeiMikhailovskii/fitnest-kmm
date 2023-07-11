package com.fitnest.presentation.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.fitnest.presentation.internal.ErrorHandlerDelegate
import com.fitnest.presentation.navigation.Route
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import com.fitnest.presentation.view.ButtonWithProgress
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.kodein.di.compose.rememberInstance

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(viewModel: OnboardingViewModel, onNavigate: (Route) -> Unit) {
    val screenState: OnboardingState by viewModel.screenDataFlow.collectAsState()
    val errorHandlerDelegate: ErrorHandlerDelegate by rememberInstance()

    LaunchedEffect(key1 = null) {
        launch { viewModel.routeSharedFlow.collect(onNavigate) }
        launch { viewModel.failureSharedFlow.collect(errorHandlerDelegate::defaultHandleFailure) }
    }

    Scaffold(
        floatingActionButton = {
            ButtonWithProgress(
                size = Dimen.Dimen50,
                previousProgress = screenState.previousProgress,
                progress = screenState.progress,
                onClick = viewModel::navigateToNextScreen
            ) {
                Image(
                    painter = painterResource("ic_onboarding_arrow_right.xml"),
                    contentDescription = null
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Column(modifier = Modifier.padding(it)) {
            Image(
                painter = painterResource(screenState.imageRes),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = stringResource(screenState.title),
                modifier = Modifier.padding(top = Padding.Padding30, start = Padding.Padding30),
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
            Text(
                text = stringResource(screenState.description),
                modifier = Modifier.padding(
                    top = Padding.Padding15,
                    start = Padding.Padding30,
                    end = Padding.Padding30
                ),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}
