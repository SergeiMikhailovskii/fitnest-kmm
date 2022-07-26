package com.fitnest.android.screen.private_area.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.fitnest.android.R
import com.fitnest.android.screen.private_area.home.HomeViewModel
import com.fitnest.android.screen.private_area.home.data.HomeScreenData
import com.fitnest.android.style.*

@Composable
internal fun HeaderBlock(
    headerWidget: HomeScreenData.HeaderWidget,
    viewModel: HomeViewModel
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
                style = PoppinsNormalStyle12Gray2
            )
            Text(
                text = headerWidget.name.orEmpty(),
                modifier = Modifier.padding(top = Padding.Padding5),
                style = PoppinsBoldStyle20Black
            )
        }
        Spacer(modifier = Modifier.weight(1F))
        Box(
            modifier = Modifier
                .width(Dimen.Dimen40)
                .height(Dimen.Dimen40)
                .clip(RoundedCornerShape(Dimen.Dimen8))
                .background(BorderColor)
                .clickable {
                    viewModel.navigateToNotifications()
                },
            contentAlignment = Alignment.Center
        ) {
            val notificationRes = if (headerWidget.hasNotifications == true)
                R.drawable.ic_private_area_has_notifications
            else R.drawable.ic_private_area_no_notifications

            Image(
                painter = painterResource(id = notificationRes),
                contentDescription = null
            )
        }
    }
}
