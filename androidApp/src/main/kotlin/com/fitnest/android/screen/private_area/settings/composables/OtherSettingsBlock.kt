package com.fitnest.android.screen.private_area.settings.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.android.R
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding

@Preview
@Composable
private fun OtherSettingsBlockPreview() {
    OtherSettingsBlock(modifier = Modifier)
}

@Composable
internal fun OtherSettingsBlock(modifier: Modifier) {
    Card(
        modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.inverseOnSurface),
        elevation = CardDefaults.cardElevation(defaultElevation = Dimen.Dimen20)
    ) {
        Column(modifier = Modifier.padding(Padding.Padding20)) {
            Text(
                text = stringResource(id = R.string.private_area_profile_screen_other_title),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
            SettingsItem(
                icon = R.drawable.ic_private_area_profile_contact_us,
                title = R.string.private_area_profile_screen_other_contact_us_item,
                modifier = Modifier.padding(top = Padding.Padding15)
            )
            SettingsItem(
                icon = R.drawable.ic_private_area_profile_privacy_policy,
                title = R.string.private_area_profile_screen_other_privacy_policy_item,
                modifier = Modifier.padding(top = Padding.Padding10)
            )
            SettingsItem(
                icon = R.drawable.ic_private_area_profile_settings,
                title = R.string.private_area_profile_screen_other_settings_item,
                modifier = Modifier.padding(top = Padding.Padding10)
            )
        }
    }
}