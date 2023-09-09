package com.fitnest.presentation.screen.privateArea.settings.composables

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
import com.fitnest.accompanistmultiplatform.PlaceholderHighlightMultiplatform
import com.fitnest.accompanistmultiplatform.fade
import com.fitnest.accompanistmultiplatform.placeholder
import com.fitnest.domain.extension.orZero
import com.fitnest.presentation.MR
import com.fitnest.presentation.screen.privateArea.settings.data.SettingsScreenData
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import dev.icerock.moko.resources.compose.localized
import dev.icerock.moko.resources.compose.stringResource
import dev.icerock.moko.resources.format
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ProfileInfoBlock(
    modifier: Modifier,
    screenData: SettingsScreenData,
    progress: Boolean
) {
    Column(modifier = modifier) {
        Row {
            Image(
                painter = painterResource("ic_private_area_activity_water.xml"),
                contentDescription = null,
                modifier = Modifier.size(Dimen.Dimen55)
            )
            Column(
                modifier = Modifier
                    .weight(1F)
                    .padding(start = Padding.Padding15)
            ) {
                Text(
                    screenData.name.orEmpty(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.placeholder(
                        progress,
                        highlight = PlaceholderHighlightMultiplatform.fade(),
                        shape = RoundedCornerShape(Dimen.Dimen16)
                    ).run {
                        if (progress) {
                            width(Dimen.Dimen135)
                        } else {
                            this
                        }
                    }
                )
                Text(
                    MR.strings.private_area_profile_screen_program_description.format(
                        screenData.program?.let { stringResource(it) }.orEmpty()
                    ).localized(),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier.padding(top = Padding.Padding5)
                        .placeholder(
                            progress,
                            highlight = PlaceholderHighlightMultiplatform.fade(),
                            shape = RoundedCornerShape(Dimen.Dimen16)
                        ).run {
                            if (progress) {
                                width(Dimen.Dimen135)
                            } else {
                                this
                            }
                        }
                )
            }
            Button(
                onClick = {
                    if (!progress) {
                        // handle click here
                    }
                },
                modifier = Modifier.placeholder(
                    progress,
                    highlight = PlaceholderHighlightMultiplatform.fade(),
                    shape = CircleShape
                )
            ) {
                Text(
                    text = stringResource(MR.strings.private_area_profile_screen_profile_edit),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Row(
            modifier = Modifier.padding(
                top = Padding.Padding15
            )
        ) {
            ProfileUserInfoCard(
                modifier = Modifier
                    .weight(1F)
                    .placeholder(
                        progress,
                        highlight = PlaceholderHighlightMultiplatform.fade(),
                        shape = RoundedCornerShape(Dimen.Dimen16)
                    ),
                value = stringResource(
                    MR.strings.private_area_profile_screen_profile_height_value,
                    screenData.height.orZero
                ),
                description = stringResource(MR.strings.private_area_profile_screen_profile_height_label)
            )
            Box(modifier = Modifier.width(Dimen.Dimen15))
            ProfileUserInfoCard(
                modifier = Modifier
                    .weight(1F)
                    .placeholder(
                        progress,
                        highlight = PlaceholderHighlightMultiplatform.fade(),
                        shape = RoundedCornerShape(Dimen.Dimen16)
                    ),
                value = stringResource(
                    MR.strings.private_area_profile_screen_profile_weight_value,
                    screenData.weight.orZero
                ),
                description = stringResource(MR.strings.private_area_profile_screen_profile_weight_label)
            )
            Box(modifier = Modifier.width(Dimen.Dimen15))
            ProfileUserInfoCard(
                modifier = Modifier
                    .weight(1F)
                    .placeholder(
                        progress,
                        highlight = PlaceholderHighlightMultiplatform.fade(),
                        shape = RoundedCornerShape(Dimen.Dimen16)
                    ),
                value = stringResource(
                    MR.strings.private_area_profile_screen_profile_age_value,
                    screenData.age.orZero
                ),
                description = stringResource(MR.strings.private_area_profile_screen_profile_age_label)
            )
        }
    }
}
