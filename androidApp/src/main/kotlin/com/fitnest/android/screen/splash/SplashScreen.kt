package com.fitnest.android.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.extension.brandGradient
import com.fitnest.android.internal.ErrorHandlerDelegate
import com.fitnest.android.navigation.handleNavigation
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Padding
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.compose.localDI
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.subDI

@Composable
internal fun SplashScreen(navController: NavController, diSubModule: DI.Module) = subDI(
    diBuilder = { import(diSubModule) }
) {
    val di = localDI()
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance { di }
    val errorHandlerDelegate: ErrorHandlerDelegate by rememberInstance()

    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = SplashViewModel::class.java
    )

    var progress by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = null) {
        launch {
            viewModel.routeSharedFlow.collect {
                handleNavigation(route = it, navController = navController)
            }
        }
        launch {
            viewModel.progressStateFlow.collect {
                progress = it
            }
        }
        launch {
            viewModel.failureSharedFlow.collect(errorHandlerDelegate::defaultHandleFailure)
        }
        viewModel.generateToken()
    }

    Box(
        modifier = Modifier
            .background(brush = Brush.verticalGradient(colors = MaterialTheme.colorScheme.brandGradient))
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_splash_logo),
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center)
        )
        if (!progress) {
            FilledTonalButton(
                onClick = viewModel::navigateNext,
                modifier = Modifier
                    .padding(
                        start = Padding.Padding30,
                        end = Padding.Padding30,
                        bottom = Padding.Padding40
                    )
                    .height(Dimen.Dimen60)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
            ) {
                Text(
                    text = stringResource(id = R.string.splash_button_title),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}
