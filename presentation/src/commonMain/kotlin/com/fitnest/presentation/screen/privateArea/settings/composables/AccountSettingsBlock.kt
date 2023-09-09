package com.fitnest.presentation.screen.privateArea.settings.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.fitnest.presentation.MR
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun AccountSettingsBlock(
    modifier: Modifier,
    onActivityHistoryClicked: () -> Unit
) {
    Card(
        modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.inverseOnSurface),
        elevation = CardDefaults.cardElevation(defaultElevation = Dimen.Dimen20)
    ) {
        Column(modifier = Modifier.padding(Padding.Padding20)) {
            Text(
                text = stringResource(MR.strings.private_area_profile_screen_account_title),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
            SettingsItem(
                icon = "ic_private_area_profile_profile.xml",
                title = MR.strings.private_area_profile_screen_account_personal_data_item,
                modifier = Modifier.padding(top = Padding.Padding15)
            )
            SettingsItem(
                icon = "ic_private_area_profile_document.xml",
                title = MR.strings.private_area_profile_screen_account_achievement_item,
                modifier = Modifier.padding(top = Padding.Padding10)
            )
            SettingsItem(
                icon = "ic_private_area_profile_activity.xml",
                title = MR.strings.private_area_profile_screen_account_activity_history_item,
                modifier = Modifier
                    .padding(top = Padding.Padding10)
                    .clickable { onActivityHistoryClicked() }
            )
            SettingsItem(
                icon = "ic_private_area_profile_chart.xml",
                title = MR.strings.private_area_profile_screen_account_workout_progress_item,
                modifier = Modifier.padding(top = Padding.Padding10)
            )
        }
    }
}
