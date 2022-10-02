package com.fitnest.android.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
import com.fitnest.android.internal.ErrorHandlerDelegate
import com.fitnest.android.navigation.handleNavigation
import com.fitnest.android.style.BrandGradient
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Padding
import com.fitnest.android.style.TextSize
import com.fitnest.android.style.poppinsFamily
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.kodein.di.compose.rememberInstance

@Composable
fun SplashScreen(navController: NavController) {
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()
    val errorHandlerDelegate: ErrorHandlerDelegate by rememberInstance()

    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = SplashViewModel::class.java
    )

    var progress: Boolean? by remember { mutableStateOf(null) }

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

    Scaffold {
        Box(
            modifier = Modifier
                .background(brush = Brush.verticalGradient(colors = BrandGradient))
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_splash_logo),
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center)
            )
            if (progress == false) {
                OutlinedButton(
                    onClick = {
                        viewModel.navigateNext()
                    },
                    shape = CircleShape,
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
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = TextSize.Size16
                    )
                }
            }
        }
    }
}
