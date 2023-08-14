package com.fitnest.presentation.screen.privateArea.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.fitnest.presentation.extension.PlaceholderHighlightMultiplatform
import com.fitnest.presentation.extension.fade
import com.fitnest.presentation.extension.placeholder
import com.fitnest.presentation.internal.ErrorHandlerDelegate
import com.fitnest.presentation.navigation.Route
import com.fitnest.presentation.screen.privateArea.settings.composables.AccountSettingsBlock
import com.fitnest.presentation.screen.privateArea.settings.composables.NotificationSettingsBlock
import com.fitnest.presentation.screen.privateArea.settings.composables.OtherSettingsBlock
import com.fitnest.presentation.screen.privateArea.settings.composables.ProfileInfoBlock
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import kotlinx.coroutines.launch
import org.kodein.di.compose.rememberInstance

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onNavigate: (Route) -> Unit,
    onEnableNotifications: (Boolean) -> Unit
) {
    val errorHandlerDelegate: ErrorHandlerDelegate by rememberInstance()
    val screenData by viewModel.screenDataFlow.collectAsState()
    val progress by viewModel.progressSharedFlow.collectAsState(false)

    LaunchedEffect(null) {
        launch { viewModel.routeSharedFlow.collect(onNavigate) }
        launch { viewModel.failureSharedFlow.collect(errorHandlerDelegate::defaultHandleFailure) }
        viewModel.getProfilePage()
    }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        ProfileInfoBlock(
            modifier = Modifier.padding(
                top = Padding.Padding30,
                start = Padding.Padding30,
                end = Padding.Padding30
            ),
            screenData = screenData,
            progress = progress
        )
        AccountSettingsBlock(
            modifier = Modifier.padding(
                top = Padding.Padding30,
                start = Padding.Padding30,
                end = Padding.Padding30
            ),
            onActivityHistoryClicked = viewModel::onActivityHistoryClicked
        )
        NotificationSettingsBlock(
            modifier = Modifier
                .padding(
                    top = Padding.Padding15,
                    start = Padding.Padding30,
                    end = Padding.Padding30
                )
                .placeholder(
                    progress,
                    highlight = PlaceholderHighlightMultiplatform.fade(),
                    shape = RoundedCornerShape(Dimen.Dimen16)
                ),
            screenData = screenData,
            onNotificationCheckedChange = {
                if (it) {
                    onEnableNotifications(it)
                } else {
                    viewModel.setNotificationsEnabled(false)
                }
            }
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
