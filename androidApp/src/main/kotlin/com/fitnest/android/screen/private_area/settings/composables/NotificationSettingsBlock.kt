package com.fitnest.android.screen.private_area.settings.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.android.R
import com.fitnest.android.screen.private_area.settings.data.SettingsScreenData
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Padding

@Preview
@Composable
private fun NotificationSettingsBlockPreview() {
    NotificationSettingsBlock(modifier = Modifier, screenData = SettingsScreenData()) {}
}

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
                text = stringResource(id = R.string.private_area_profile_screen_notification_title),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
            SettingsItem(
                icon = R.drawable.ic_private_area_profile_notification,
                title = R.string.private_area_profile_screen_notification_item,
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