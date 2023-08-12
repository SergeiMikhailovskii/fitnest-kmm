package com.fitnest.presentation.screen.registration.welcomeBack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.fitnest.presentation.MR
import com.fitnest.presentation.internal.ErrorHandlerDelegate
import com.fitnest.presentation.navigation.Route
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.kodein.di.compose.rememberInstance

@OptIn(ExperimentalResourceApi::class)
@Composable
fun WelcomeBackRegistrationScreen(
    viewModel: WelcomeBackRegistrationViewModel,
    onNavigate: (Route) -> Unit
) {
    val errorHandlerDelegate: ErrorHandlerDelegate by rememberInstance()

    val screenData by viewModel.screenDataFlow.collectAsState()

    LaunchedEffect(null) {
        launch { viewModel.routeSharedFlow.collect(onNavigate) }
        launch { viewModel.failureSharedFlow.collect(errorHandlerDelegate::defaultHandleFailure) }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource("ic_registration_welcome_back.xml"),
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
                MR.strings.registration_welcome_back_title,
                screenData.name.orEmpty()
            ),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(
                top = Padding.Padding44,
                start = Padding.Padding50,
                end = Padding.Padding50
            )
        )
        Text(
            text = stringResource(MR.strings.registration_welcome_back_description),
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
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
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(MR.strings.registration_welcome_back_next_button_label),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}
