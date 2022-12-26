package com.fitnest.android.screen.private_area.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitnest.android.internal.ErrorHandlerDelegate
import com.fitnest.android.screen.private_area.settings.composables.AccountSettingsBlock
import com.fitnest.android.screen.private_area.settings.composables.NotificationSettingsBlock
import com.fitnest.android.screen.private_area.settings.composables.OtherSettingsBlock
import com.fitnest.android.screen.private_area.settings.composables.ProfileInfoBlock
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Padding
import kotlinx.coroutines.launch
import org.kodein.di.compose.rememberInstance

@Preview
@Composable
internal fun SettingsScreen() {
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()
    val errorHandlerDelegate: ErrorHandlerDelegate by rememberInstance()

    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = SettingsViewModel::class.java
    )

    LaunchedEffect(null) {
        launch {
            viewModel.routeSharedFlow.collect {
//                handleNavigation(it, navController)
            }
        }
        launch {
            viewModel.failureSharedFlow.collect(errorHandlerDelegate::defaultHandleFailure)
        }
        viewModel.getProfilePage()
    }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        ProfileInfoBlock(
            modifier = Modifier.padding(
                top = Padding.Padding30,
                start = Padding.Padding30,
                end = Padding.Padding30
            )
        )
        AccountSettingsBlock(
            modifier = Modifier.padding(
                top = Padding.Padding30,
                start = Padding.Padding30,
                end = Padding.Padding30
            )
        )
        NotificationSettingsBlock(
            modifier = Modifier.padding(
                top = Padding.Padding15,
                start = Padding.Padding30,
                end = Padding.Padding30
            )
        )
        OtherSettingsBlock(
            modifier = Modifier.padding(
                top = Padding.Padding15,
                start = Padding.Padding30,
                end = Padding.Padding30
            )
        )
        Box(modifier = Modifier.height(Dimen.Dimen20))
    }
}