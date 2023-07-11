package com.fitnest.android.screen.private_area.home.composable

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.fitnest.android.R
import com.fitnest.android.screen.private_area.home.HomeViewModel
import com.fitnest.android.screen.private_area.home.data.HomeScreenData
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder

@Composable
internal fun HeaderBlock(
    headerWidget: HomeScreenData.HeaderWidget?,
    viewModel: HomeViewModel,
    progress: Boolean
) {
    Row(
        modifier = Modifier.padding(
            top = Padding.Padding20,
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.private_area_dashboard_header_welcome_back),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                modifier = Modifier.placeholder(progress, highlight = PlaceholderHighlight.fade())
            )
            Text(
                text = headerWidget?.name.orEmpty(),
                modifier = Modifier
                    .padding(top = Padding.Padding5)
                    .placeholder(progress, highlight = PlaceholderHighlight.fade())
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
                .placeholder(progress, highlight = PlaceholderHighlight.fade())
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable { if (!progress) viewModel.navigateToNotifications() },
            contentAlignment = Alignment.Center
        ) {
            val notificationRes = if (headerWidget?.hasNotifications == true)
                R.drawable.ic_private_area_has_notifications
            else R.drawable.ic_private_area_no_notifications

            Image(
                painter = painterResource(id = notificationRes),
                contentDescription = null
            )
        }
    }
}
