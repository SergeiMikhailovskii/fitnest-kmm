package com.fitnest.android.view.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.base.Route
import com.fitnest.android.style.*
import com.fitnest.domain.entity.OnboardingState
import org.kodein.di.compose.instance
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.withDI

@Composable
fun SplashScreen(navController: NavController) {
    val viewModel: SplashViewModel by rememberInstance()

    LaunchedEffect(key1 = null) {
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
            OutlinedButton(
                onClick = {
                    navController.navigate(Route.Onboarding(OnboardingState.FIRST_SCREEN_PROGRESS).screenName) {
                        popUpTo(Route.Splash.screenName) { inclusive = true }
                    }
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