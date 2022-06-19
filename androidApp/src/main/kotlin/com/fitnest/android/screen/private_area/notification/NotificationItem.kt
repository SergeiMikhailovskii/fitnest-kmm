package com.fitnest.android.screen.private_area.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.android.R
import com.fitnest.android.style.GrayColor2
import com.fitnest.android.style.Padding
import com.fitnest.android.style.PoppinsMediumStyle10Gray1
import com.fitnest.android.style.PoppinsMediumStyle12Black

@Preview
@Composable
private fun NotificationItemPreview() {
    NotificationItem(title = "Hey, it’s time for lunch", description = "About 1 minutes ago")
}

@Composable
internal fun NotificationItem(
    title: String,
    description: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Padding.Padding16),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_private_area_notification_meal),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .padding(horizontal = Padding.Padding10)
                .fillMaxHeight()
                .weight(1F),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title, style = PoppinsMediumStyle12Black)
            Text(description, style = PoppinsMediumStyle10Gray1)
        }
        Icon(Icons.Default.MoreVert, contentDescription = null, tint = GrayColor2)
    }
}