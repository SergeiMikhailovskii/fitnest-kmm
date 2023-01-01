package com.fitnest.android.screen.private_area.activity_tracker.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.fitnest.android.R
import com.fitnest.android.extension.brandGradient
import com.fitnest.android.screen.private_area.home.data.HomeScreenData
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.Padding
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder

@Preview
@Composable
internal fun TodayTargetBlockPreview() {
    TodayTargetBlock(
        modifier = Modifier,
        data = HomeScreenData.TodayTargetWidget(waterIntake = "8L", steps = "2400"),
        onAddActivityClicked = {},
        progress = false
    )
}

@Composable
internal fun TodayTargetBlock(
    modifier: Modifier,
    data: HomeScreenData.TodayTargetWidget,
    onAddActivityClicked: () -> Unit,
    progress: Boolean
) {
    Column(
        modifier = modifier
            .background(
                brush = Brush.horizontalGradient(MaterialTheme.colorScheme.brandGradient),
                alpha = 0.2F,
                shape = RoundedCornerShape(Dimen.Dimen22)
            )
            .placeholder(
                progress,
                highlight = PlaceholderHighlight.fade(),
                shape = RoundedCornerShape(Dimen.Dimen22)
            )
            .padding(Padding.Padding20)
    ) {
        Row {
            Text(
                stringResource(id = R.string.private_area_activity_tracker_screen_today_target_title),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Box(modifier = Modifier.weight(1F))
            Button(
                onClick = onAddActivityClicked,
                contentPadding = PaddingValues(),
                modifier = Modifier
                    .width(Dimen.Dimen24)
                    .height(Dimen.Dimen24),
                shape = RoundedCornerShape(Dimen.Dimen8)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(Dimen.Dimen8))
                        .background(brush = Brush.horizontalGradient(MaterialTheme.colorScheme.brandGradient))
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                }
            }
        }
        Row {
            TargetViewBlock(
                modifier = Modifier
                    .padding(top = Padding.Padding15)
                    .weight(1F),
                icon = R.drawable.ic_activity_tracker_water,
                amountText = data.waterIntake,
                indexTitle = R.string.private_area_activity_tracker_screen_water_index
            )
            TargetViewBlock(
                modifier = Modifier
                    .padding(
                        top = Padding.Padding15,
                        start = Padding.Padding15
                    )
                    .weight(1F),
                icon = R.drawable.ic_activity_tracker_steps,
                amountText = data.steps,
                indexTitle = R.string.private_area_activity_tracker_screen_steps_index
            )
        }
    }
}

@Preview
@Composable
private fun TargetViewBlockPreview() {
    TargetViewBlock(
        modifier = Modifier,
        icon = R.drawable.ic_activity_tracker_water,
        amountText = "8L",
        indexTitle = R.string.private_area_activity_tracker_screen_water_index
    )
}

@Composable
private fun TargetViewBlock(
    modifier: Modifier,
    @DrawableRes icon: Int,
    amountText: String,
    @StringRes indexTitle: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(Dimen.Dimen12))
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(all = Padding.Padding10)
    ) {
        Image(painter = painterResource(id = icon), contentDescription = null)
        Column(modifier = Modifier.padding(start = Padding.Padding8)) {
            Text(
                amountText,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                stringResource(id = indexTitle),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}