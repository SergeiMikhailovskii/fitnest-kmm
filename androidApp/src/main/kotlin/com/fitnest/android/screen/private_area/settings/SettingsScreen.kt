package com.fitnest.android.screen.private_area.settings

import android.os.Build
import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fitnest.android.di.PrivateAreaModule
import com.fitnest.android.internal.ErrorHandlerDelegate
import com.fitnest.android.navigation.handleNavigation
import com.fitnest.android.screen.private_area.settings.composables.AccountSettingsBlock
import com.fitnest.android.screen.private_area.settings.composables.NotificationSettingsBlock
import com.fitnest.android.screen.private_area.settings.composables.OtherSettingsBlock
import com.fitnest.android.screen.private_area.settings.composables.ProfileInfoBlock
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Padding
import com.google.accompanist.navigation.animation.AnimatedComposeNavigator
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import kotlinx.coroutines.launch
import org.kodein.di.compose.localDI
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.subDI

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen(navController = rememberAnimatedNavController(AnimatedComposeNavigator()))
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun SettingsScreen(navController: NavController) = subDI(diBuilder = {
    import(PrivateAreaModule.settingsPrivateAreaModule)
}) {
    val di = localDI()
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance { di }
    val errorHandlerDelegate: ErrorHandlerDelegate by rememberInstance()
    val notificationsPermissionState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        rememberPermissionState(android.Manifest.permission.POST_NOTIFICATIONS)
    } else {
        error("Notification permission shouldn't be requested for version ${Build.VERSION.SDK_INT}")
    }

    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = SettingsViewModel::class.java
    )
    val screenData by viewModel.screenDataFlow.collectAsState()
    val progress by viewModel.progressSharedFlow.collectAsState(false)

    LaunchedEffect(null) {
        launch {
            viewModel.routeSharedFlow.collect {
                handleNavigation(it, navController)
            }
        }
        launch {
            viewModel.failureSharedFlow.collect(errorHandlerDelegate::defaultHandleFailure)
        }
        viewModel.getProfilePage()
    }

    if (notificationsPermissionState.status.isGranted) {
        viewModel.setNotificationsEnabled(true)
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
                    highlight = PlaceholderHighlight.fade(),
                    shape = RoundedCornerShape(Dimen.Dimen16)
                ),
            screenData = screenData,
            onNotificationCheckedChange = {
                if (it) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        notificationsPermissionState.launchPermissionRequest()
                    } else {
                        viewModel.setNotificationsEnabled(true)
                    }
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