package com.fitnest.android.screen.private_area.activity_tracker.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.android.R
import com.fitnest.android.screen.private_area.activity_tracker.data.ActivityTrackerScreenData
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.GrayColor2
import com.fitnest.android.style.Padding
import com.fitnest.android.style.PoppinsMediumStyle12Black
import com.fitnest.android.style.PoppinsNormalStyle10Gray2
import com.fitnest.android.style.PoppinsSemiBoldStyle16Black
import com.fitnest.domain.enum.ActivityType

@Preview
@Composable
internal fun LatestActivityBlockPreview() {
    LatestActivityBlock(
        modifier = Modifier,
        activities = listOf(
            ActivityTrackerScreenData.Activity(
                "Drinking 300ml Water",
                "About 3 minutes ago",
                ActivityType.WATER
            ),
            ActivityTrackerScreenData.Activity(
                "Drinking 300ml Water",
                "About 3 minutes ago",
                ActivityType.WATER
            ),
            ActivityTrackerScreenData.Activity(
                "Drinking 300ml Water",
                "About 3 minutes ago",
                ActivityType.WATER
            ),
        )
    )
}

@Preview
@Composable
internal fun LatestActivityItemPreview() {
    LatestActivityItem(
        ActivityTrackerScreenData.Activity(
            "Drinking 300ml Water",
            "About 3 minutes ago",
            ActivityType.WATER
        ),
    )
}

@Composable
internal fun LatestActivityBlock(
    modifier: Modifier,
    activities: List<ActivityTrackerScreenData.Activity>? = null
) {
    Column(modifier = modifier) {
        Text(
            stringResource(id = R.string.private_area_activity_tracker_screen_latest_activity_title),
            style = PoppinsSemiBoldStyle16Black
        )
        activities?.forEach {
            LatestActivityItem(activity = it)
        }
    }
}

@Composable
private fun LatestActivityItem(activity: ActivityTrackerScreenData.Activity) {
    Card(
        modifier = Modifier
            .padding(top = Padding.Padding15)
            .clip(RoundedCornerShape(Dimen.Dimen16))
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(Padding.Padding15),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_private_area_notification_meal),
                contentDescription = null
            )
            Column(modifier = Modifier.padding(start = Padding.Padding8)) {
                Text(activity.title, style = PoppinsMediumStyle12Black)
                Text(
                    activity.description,
                    modifier = Modifier.padding(top = Padding.Padding3),
                    style = PoppinsNormalStyle10Gray2
                )
            }
            Box(modifier = Modifier.weight(1F))
            Icon(Icons.Default.MoreVert, contentDescription = null, tint = GrayColor2)
        }
    }
}
