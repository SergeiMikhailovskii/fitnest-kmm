package com.fitnest.android.screen.private_area.settings

import android.os.Build
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitnest.android.di.PrivateAreaModule
import com.fitnest.presentation.navigation.Route
import com.fitnest.presentation.screen.privateArea.settings.SettingsViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.subDI

@Preview
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen {}
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun SettingsScreen(onNavigate: (Route) -> Unit) = subDI(diBuilder = {
    import(PrivateAreaModule.settingsPrivateAreaModule)
}) {
    val viewModelFactory: ViewModelProvider.Factory by rememberInstance()
    val notificationsPermissionState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        rememberPermissionState(android.Manifest.permission.POST_NOTIFICATIONS)
    } else {
        error("Notification permission shouldn't be requested for version ${Build.VERSION.SDK_INT}")
    }

    val viewModel = viewModel(
        factory = viewModelFactory,
        modelClass = SettingsViewModel::class.java
    )

    if (notificationsPermissionState.status.isGranted) {
        viewModel.setNotificationsEnabled(true)
    }

    com.fitnest.presentation.screen.privateArea.settings.SettingsScreen(
        viewModel = viewModel,
        onNavigate = onNavigate,
        onEnableNotifications = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                notificationsPermissionState.launchPermissionRequest()
            } else {
                viewModel.setNotificationsEnabled(true)
            }
        }
    )
}