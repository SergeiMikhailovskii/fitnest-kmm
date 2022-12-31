package com.fitnest.android.screen.private_area.settings.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.android.R
import com.fitnest.android.screen.private_area.settings.data.SettingsScreenData
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Padding

@Preview
@Composable
private fun ProfileInfoBlockPreview() {
    ProfileInfoBlock(modifier = Modifier, screenData = SettingsScreenData())
}

@Composable
internal fun ProfileInfoBlock(modifier: Modifier, screenData: SettingsScreenData) {
    Column(modifier = modifier) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.ic_private_area_activity_water),
                contentDescription = null,
                modifier = Modifier.size(Dimen.Dimen55)
            )
            Column(
                modifier = Modifier
                    .weight(1F)
                    .padding(start = Padding.Padding15)
            ) {
                Text(screenData.name.orEmpty(), style = MaterialTheme.typography.bodyMedium)
                Text(
                    screenData.program.orEmpty(), style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }
            Button(onClick = { }) {
                Text(
                    text = stringResource(id = R.string.private_area_profile_screen_profile_edit),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Row(
            modifier = Modifier.padding(
                top = Padding.Padding15,
            )
        ) {
            ProfileUserInfoCard(
                modifier = Modifier.weight(1F),
                value = stringResource(
                    R.string.private_area_profile_screen_profile_height_value,
                    screenData.height ?: 0
                ),
                description = stringResource(id = R.string.private_area_profile_screen_profile_height_label)
            )
            Box(modifier = Modifier.width(Dimen.Dimen15))
            ProfileUserInfoCard(
                modifier = Modifier.weight(1F),
                value = stringResource(
                    id = R.string.private_area_profile_screen_profile_weight_value,
                    screenData.weight ?: 0
                ),
                description = stringResource(id = R.string.private_area_profile_screen_profile_weight_label)
            )
            Box(modifier = Modifier.width(Dimen.Dimen15))
            ProfileUserInfoCard(
                modifier = Modifier.weight(1F),
                value = stringResource(
                    id = R.string.private_area_profile_screen_profile_age_value,
                    screenData.age ?: 0
                ),
                description = stringResource(id = R.string.private_area_profile_screen_profile_age_label)
            )
        }
    }
}