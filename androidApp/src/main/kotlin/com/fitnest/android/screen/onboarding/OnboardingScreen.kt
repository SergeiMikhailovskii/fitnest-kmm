package com.fitnest.android.screen.onboarding

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.di.onboardingModule
import com.fitnest.android.internal.ErrorHandlerDelegate
import com.fitnest.android.navigation.handleNavigation
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Padding
import com.fitnest.android.view.ui_elements.ButtonWithProgress
import com.fitnest.domain.entity.OnboardingState
import kotlinx.coroutines.launch
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.subDI

@ExperimentalMaterial3Api
@Composable
fun OnboardingScreen(navController: NavController, stepName: String) = subDI(diBuilder = {
    import(module = onboardingModule, allowOverride = true)
}) {
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()

    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = OnboardingViewModel::class.java
    )
    val screenState: OnboardingState? by viewModel.stateLiveData.observeAsState(null)
    val errorHandlerDelegate: ErrorHandlerDelegate by rememberInstance()

    LaunchedEffect(key1 = null) {
        viewModel.updateScreenState(stepName)
        launch {
            viewModel.routeSharedFlow.collect {
                handleNavigation(route = it, navController = navController)
            }
        }
        launch {
            viewModel.failureSharedFlow.collect(errorHandlerDelegate::defaultHandleFailure)
        }
    }

    Scaffold(
        floatingActionButton = {
            screenState?.let {
                ButtonWithProgress(
                    size = Dimen.Dimen50,
                    previousProgress = it.previousProgress,
                    progress = it.progress,
                    onClick = viewModel::navigateToNextScreen
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_onboarding_arrow_right),
                        contentDescription = null
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Column(modifier = Modifier.padding(it)) {
            Image(
                painter = painterResource(
                    id = screenState?.imageResId ?: R.drawable.ic_onboarding_first
                ),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = stringResource(
                    id = screenState?.title ?: R.string.onboarding_first_title
                ),
                modifier = Modifier.padding(top = Padding.Padding30, start = Padding.Padding30),
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
            Text(
                text = stringResource(
                    id = screenState?.description ?: R.string.onboarding_first_description
                ),
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
