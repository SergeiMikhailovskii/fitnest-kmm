package com.fitnest.presentation.screen.privateArea.settings.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.fitnest.presentation.MR
import com.fitnest.presentation.screen.privateArea.settings.data.SettingsScreenData
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import dev.icerock.moko.resources.compose.stringResource

@Composable
internal fun NotificationSettingsBlock(
    modifier: Modifier,
    screenData: SettingsScreenData,
    onNotificationCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.inverseOnSurface),
        elevation = CardDefaults.cardElevation(defaultElevation = Dimen.Dimen20)
    ) {
        Column(modifier = Modifier.padding(Padding.Padding20)) {
            Text(
                text = stringResource(MR.strings.private_area_profile_screen_notification_title),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
            SettingsItem(
                icon = "ic_private_area_profile_notification.xml",
                title = MR.strings.private_area_profile_screen_notification_item,
                modifier = Modifier.padding(top = Padding.Padding15),
                trailing = {
                    Switch(
                        checked = screenData.areNotificationsEnabled,
                        onCheckedChange = onNotificationCheckedChange
                    )
                }
            )
        }
    }
}
