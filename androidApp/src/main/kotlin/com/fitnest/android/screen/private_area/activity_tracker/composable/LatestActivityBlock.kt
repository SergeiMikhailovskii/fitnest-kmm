package com.fitnest.android.screen.private_area.activity_tracker.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.android.R
import com.fitnest.android.screen.private_area.activity_tracker.ActivityTrackerViewModel
import com.fitnest.android.screen.private_area.activity_tracker.data.ActivityTrackerScreenData
import com.fitnest.domain.enum.ActivityType
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import com.fitnest.presentation.R as PresentationR

@Preview
@Composable
internal fun LatestActivityBlockPreview() {
    LatestActivityBlock(
        modifier = Modifier,
        viewModel = Any() as ActivityTrackerViewModel,
        activities = persistentListOf(
            ActivityTrackerScreenData.Activity(
                1,
                "Drinking 300ml Water",
                "About 3 minutes ago",
                ActivityType.WATER,
                R.drawable.ic_private_area_activity_water
            ),
            ActivityTrackerScreenData.Activity(
                2,
                "Drinking 300ml Water",
                "About 3 minutes ago",
                ActivityType.WATER,
                R.drawable.ic_private_area_activity_water
            ),
            ActivityTrackerScreenData.Activity(
                3,
                "Drinking 300ml Water",
                "About 3 minutes ago",
                ActivityType.WATER,
                R.drawable.ic_private_area_activity_water
            ),
        )
    )
}

@Preview
@Composable
internal fun LatestActivityItemPreview() {
    LatestActivityItem(
        ActivityTrackerScreenData.Activity(
            1,
            "Drinking 300ml Water",
            "About 3 minutes ago",
            ActivityType.WATER,
            R.drawable.ic_private_area_activity_water
        ),
        Any() as ActivityTrackerViewModel
    )
}

@Composable
internal fun LatestActivityBlock(
    viewModel: ActivityTrackerViewModel,
    modifier: Modifier,
    activities: ImmutableList<ActivityTrackerScreenData.Activity>? = null
) {
    Column(modifier = modifier) {
        Text(
            stringResource(id = PresentationR.string.private_area_activity_tracker_screen_latest_activity_title),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
        )
        activities?.forEach {
            LatestActivityItem(activity = it, viewModel = viewModel)
        }
    }
}

@Composable
private fun LatestActivityItem(
    activity: ActivityTrackerScreenData.Activity,
    viewModel: ActivityTrackerViewModel
) {
    var isMoreMenuExpanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(top = Padding.Padding15)
            .fillMaxWidth(),
        shape = RoundedCornerShape(Dimen.Dimen16),
        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimen.Dimen20
        )
    ) {
        Row(
            modifier = Modifier.padding(Padding.Padding15),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = activity.icon),
                contentDescription = null
            )
            Column(modifier = Modifier.padding(start = Padding.Padding8)) {
                Text(
                    activity.title,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                )
                Text(
                    activity.description,
                    modifier = Modifier.padding(top = Padding.Padding3),
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }
            Box(modifier = Modifier.weight(1F))
            Box {
                IconButton(onClick = { isMoreMenuExpanded = true }) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                DropdownMenu(
                    expanded = isMoreMenuExpanded,
                    onDismissRequest = { isMoreMenuExpanded = false }
                ) {
                    DropdownMenuItem(
                        onClick = {
                            isMoreMenuExpanded = false
                            viewModel.deleteActivity(activity)
                        },
                        text = {
                            Text(stringResource(id = PresentationR.string.private_area_activity_tracker_screen_latest_activity_delete))
                        }
                    )
                }
            }
        }
    }
}
