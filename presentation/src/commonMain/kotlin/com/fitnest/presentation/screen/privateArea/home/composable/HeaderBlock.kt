package com.fitnest.presentation.screen.privateArea.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.fitnest.presentation.MR
import com.fitnest.presentation.extension.PlaceholderHighlightMultiplatform
import com.fitnest.presentation.extension.fade
import com.fitnest.presentation.extension.placeholder
import com.fitnest.presentation.screen.privateArea.home.HomeViewModel
import com.fitnest.presentation.screen.privateArea.home.data.HomeScreenData
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import dev.icerock.moko.resources.compose.stringResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun HeaderBlock(
    headerWidget: HomeScreenData.HeaderWidget?,
    viewModel: HomeViewModel,
    progress: Boolean
) {
    Row(
        modifier = Modifier.padding(
            top = Padding.Padding20
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = stringResource(MR.strings.private_area_dashboard_header_welcome_back),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                modifier = Modifier.placeholder(progress, highlight = PlaceholderHighlightMultiplatform.fade())
            )
            Text(
                text = headerWidget?.name.orEmpty(),
                modifier = Modifier
                    .padding(top = Padding.Padding5)
                    .placeholder(progress, highlight = PlaceholderHighlightMultiplatform.fade())
                    .run {
                        if (progress) {
                            width(Dimen.Dimen135)
                        } else {
                            this
                        }
                    },
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(modifier = Modifier.weight(1F))
        Box(
            modifier = Modifier
                .width(Dimen.Dimen40)
                .height(Dimen.Dimen40)
                .clip(RoundedCornerShape(Dimen.Dimen8))
                .placeholder(progress, highlight = PlaceholderHighlightMultiplatform.fade())
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable { if (!progress) viewModel.navigateToNotifications() },
            contentAlignment = Alignment.Center
        ) {
            val notificationRes = if (headerWidget?.hasNotifications == true) {
                "ic_private_area_has_notifications.xml"
            } else {
                "ic_private_area_no_notifications.xml"
            }

            Image(
                painter = painterResource(notificationRes),
                contentDescription = null
            )
        }
    }
}
