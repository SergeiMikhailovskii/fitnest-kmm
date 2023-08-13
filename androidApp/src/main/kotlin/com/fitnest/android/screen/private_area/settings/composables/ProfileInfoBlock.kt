package com.fitnest.android.screen.private_area.settings.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.fitnest.presentation.R as PresentationR

@Preview
@Composable
private fun ProfileInfoBlockPreview() {
    ProfileInfoBlock(modifier = Modifier, screenData = SettingsScreenData(), false)
}

@Composable
internal fun ProfileInfoBlock(
    modifier: Modifier,
    screenData: SettingsScreenData,
    progress: Boolean
) {
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
                Text(
                    screenData.name.orEmpty(), style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.placeholder(
                        progress,
                        highlight = PlaceholderHighlight.fade(),
                        shape = RoundedCornerShape(Dimen.Dimen16)
                    ).run {
                        if (progress) width(Dimen.Dimen135)
                        else this
                    }
                )
                Text(
                    screenData.program.orEmpty(),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier.padding(top = Padding.Padding5)
                        .placeholder(
                            progress,
                            highlight = PlaceholderHighlight.fade(),
                            shape = RoundedCornerShape(Dimen.Dimen16)
                        ).run {
                            if (progress) width(Dimen.Dimen135)
                            else this
                        }
                )
            }
            Button(
                onClick = {
                    if (!progress) {
                        // handle click here
                    }
                }, modifier = Modifier.placeholder(
                    progress,
                    highlight = PlaceholderHighlight.fade(),
                    shape = CircleShape
                )
            ) {
                Text(
                    text = stringResource(id = PresentationR.string.private_area_profile_screen_profile_edit),
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
                modifier = Modifier
                    .weight(1F)
                    .placeholder(
                        progress,
                        highlight = PlaceholderHighlight.fade(),
                        shape = RoundedCornerShape(Dimen.Dimen16)
                    ),
                value = stringResource(
                    PresentationR.string.private_area_profile_screen_profile_height_value,
                    screenData.height ?: 0
                ),
                description = stringResource(id = PresentationR.string.private_area_profile_screen_profile_height_label)
            )
            Box(modifier = Modifier.width(Dimen.Dimen15))
            ProfileUserInfoCard(
                modifier = Modifier
                    .weight(1F)
                    .placeholder(
                        progress,
                        highlight = PlaceholderHighlight.fade(),
                        shape = RoundedCornerShape(Dimen.Dimen16)
                    ),
                value = stringResource(
                    id = PresentationR.string.private_area_profile_screen_profile_weight_value,
                    screenData.weight ?: 0
                ),
                description = stringResource(id = PresentationR.string.private_area_profile_screen_profile_weight_label)
            )
            Box(modifier = Modifier.width(Dimen.Dimen15))
            ProfileUserInfoCard(
                modifier = Modifier
                    .weight(1F)
                    .placeholder(
                        progress,
                        highlight = PlaceholderHighlight.fade(),
                        shape = RoundedCornerShape(Dimen.Dimen16)
                    ),
                value = stringResource(
                    id = PresentationR.string.private_area_profile_screen_profile_age_value,
                    screenData.age ?: 0
                ),
                description = stringResource(id = PresentationR.string.private_area_profile_screen_profile_age_label)
            )
        }
    }
}