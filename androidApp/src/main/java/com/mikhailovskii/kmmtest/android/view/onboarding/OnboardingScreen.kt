package com.mikhailovskii.kmmtest.android.view.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
/**
 * This import should not be deleted because app will crash without it
 */
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mikhailovskii.kmmtest.android.R
import com.mikhailovskii.kmmtest.android.base.Route
import com.mikhailovskii.kmmtest.android.style.*
import com.mikhailovskii.kmmtest.android.view.ui_elements.GradientButtonWithProgress
import com.mikhailovskii.kmmtest.entity.OnboardingState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.kodein.di.compose.instance
import org.kodein.di.compose.withDI

@Composable
fun OnboardingScreen(navController: NavController, progress: Int) = withDI {
    val viewModel: OnboardingViewModel by instance()
    val screenState: OnboardingState? by viewModel.stateLiveData.observeAsState(null)

    LaunchedEffect(key1 = null) {
        viewModel.updateScreenState(progress)
        launch {
            viewModel.routeSharedFlow.collect {
                handleNavigation(route = it, navController = navController)
            }
        }
    }

    Scaffold(floatingActionButton = {
        GradientButtonWithProgress(
            gradient = Brush.horizontalGradient(BrandGradient),
            size = 50.dp,
            previousProgress = ((progress - 1) * 0.25F),
            progress = progress * 0.25F,
            onClick = {
                viewModel.navigateToNextScreen()
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_onboarding_arrow_right),
                contentDescription = null
            )
        }
    }, floatingActionButtonPosition = FabPosition.End) {
        Column {
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
                modifier = Modifier.padding(top = 30.dp, start = 30.dp),
                style = PoppinsBoldStyle24Black
            )
            Text(
                text = stringResource(
                    id = screenState?.description ?: R.string.onboarding_first_description
                ),
                modifier = Modifier.padding(top = 15.dp, start = 30.dp, end = 30.dp),
                style = PoppinsMediumStyle14Gray1
            )
        }
    }
}

fun handleNavigation(route: Route, navController: NavController) {
    when (route) {
        is Route.Onboarding -> navController.navigate(Route.Onboarding(route.progress).screenName)
    }
}