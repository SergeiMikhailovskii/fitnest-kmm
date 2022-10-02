package com.fitnest.android.screen.registration.welcome_back

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.internal.ErrorHandlerDelegate
import com.fitnest.android.navigation.handleNavigation
import com.fitnest.android.style.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.kodein.di.compose.rememberInstance

@Composable
fun WelcomeBackRegistrationScreen(navController: NavController) {
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()
    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = WelcomeBackRegistrationViewModel::class.java
    )
    val errorHandlerDelegate: ErrorHandlerDelegate by rememberInstance()

    val screenData by viewModel.screenDataFlow.collectAsState()

    LaunchedEffect(null) {
        launch {
            viewModel.routeSharedFlow.collect {
                handleNavigation(it, navController)
            }
        }
        launch {
            viewModel.failureSharedFlow.collect(errorHandlerDelegate::defaultHandleFailure)
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(
                id = R.drawable.ic_registration_welcome_back
            ),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = Padding.Padding50,
                    end = Padding.Padding50,
                    top = Padding.Padding100
                )
        )
        Text(
            text = stringResource(
                id = R.string.registration_welcome_back_title,
                screenData.name.orEmpty()
            ),
            style = PoppinsBoldStyle20Black,
            modifier = Modifier.padding(
                top = Padding.Padding44,
                start = Padding.Padding50,
                end = Padding.Padding50
            )
        )
        Text(
            text = stringResource(id = R.string.registration_welcome_back_description),
            style = PoppinsNormalStyle12Gray1,
            modifier = Modifier.padding(
                top = Padding.Padding5,
                start = Padding.Padding50,
                end = Padding.Padding50
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1F))
        Button(
            onClick = viewModel::next,
            shape = CircleShape,
            modifier = Modifier
                .padding(
                    start = Padding.Padding30,
                    end = Padding.Padding30,
                    bottom = Padding.Padding40
                )
                .height(Dimen.Dimen60)
                .fillMaxWidth(),
        ) {
            Text(
                text = stringResource(id = R.string.registration_welcome_back_next_button_label),
                style = PoppinsBoldStyle16
            )
        }
    }
}