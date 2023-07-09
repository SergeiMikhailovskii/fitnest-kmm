package com.fitnest.presentation.screen.splash

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import com.fitnest.presentation.MR
import com.fitnest.presentation.extension.brandGradient
import com.fitnest.presentation.navigation.Route
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    navigate: (Route) -> Unit
) /*= subDI(diBuilder = { import(splashModule) })*/ {
//    val errorHandlerDelegate: ErrorHandlerDelegate by rememberInstance()

    val progress by viewModel.progressSharedFlow.collectAsState(false)

    LaunchedEffect(key1 = null) {
        launch {
            viewModel.routeSharedFlow.collect {
                navigate(it)
            }
        }
//        launch {
//            viewModel.failureSharedFlow.collect(errorHandlerDelegate::defaultHandleFailure)
//        }
        viewModel.generateToken()
    }

    Box(
        modifier = Modifier
            .background(brush = Brush.verticalGradient(colors = MaterialTheme.colorScheme.brandGradient))
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource("ic_splash_logo.xml"),
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
                    .align(Alignment.BottomCenter)
                    .testTag("splash_btn_next")
            ) {
                Text(
                    text = stringResource(MR.strings.splash_button_title),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}
