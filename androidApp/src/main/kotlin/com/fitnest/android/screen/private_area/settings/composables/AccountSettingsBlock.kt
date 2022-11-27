package com.fitnest.android.screen.private_area.settings.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.android.R
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Padding

@Preview
@Composable
private fun AccountSettingsBlockPreview() {
    AccountSettingsBlock(modifier = Modifier)
}

@Composable
internal fun AccountSettingsBlock(modifier: Modifier) {
    Card(
        modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.inverseOnSurface),
        elevation = CardDefaults.cardElevation(defaultElevation = Dimen.Dimen20)
    ) {
        Column(modifier = Modifier.padding(Padding.Padding20)) {
            Text(
                text = "Account",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
            SettingsItem(
                icon = R.drawable.ic_private_area_profile_profile,
                title = R.string.private_area_profile_screen_account_personal_data_item,
                modifier = Modifier.padding(top = Padding.Padding15)
            )
            SettingsItem(
                icon = R.drawable.ic_private_area_profile_document,
                title = R.string.private_area_profile_screen_account_achievement_item,
                modifier = Modifier.padding(top = Padding.Padding10)
            )
            SettingsItem(
                icon = R.drawable.ic_private_area_profile_activity,
                title = R.string.private_area_profile_screen_account_activity_history_item,
                modifier = Modifier.padding(top = Padding.Padding10)
            )
            SettingsItem(
                icon = R.drawable.ic_private_area_profile_chart,
                title = R.string.private_area_profile_screen_account_workout_progress_item,
                modifier = Modifier.padding(top = Padding.Padding10)
            )
        }
    }
}