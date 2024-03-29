package com.fitnest.presentation.screen.privateArea.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import com.fitnest.presentation.MR
import com.fitnest.presentation.screen.privateArea.home.data.HomeScreenData
import com.fitnest.presentation.style.Dimen
import com.fitnest.presentation.style.Padding
import dev.icerock.moko.resources.compose.stringResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun LatestWorkoutBlock(latestWorkoutWidget: HomeScreenData.LatestWorkoutWidget) {
    Column(modifier = Modifier.padding(top = Padding.Padding30)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                stringResource(MR.strings.private_area_dashboard_latest_workout_title),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(modifier = Modifier.weight(1F))
            Text(
                stringResource(MR.strings.private_area_dashboard_latest_workout_see_more),
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.outline
                )
            )
        }
        latestWorkoutWidget.workouts?.forEach {
            LatestWorkoutItem(
                modifier = Modifier
                    .padding(bottom = Padding.Padding15)
                    .shadow(Dimen.Dimen40)
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = RoundedCornerShape(size = Dimen.Dimen16)
                    ),
                model = it
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun LatestWorkoutItem(modifier: Modifier, model: HomeScreenData.Workout) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Image(
            painter = painterResource("ic_private_area_workout.xml"),
            contentDescription = null,
            modifier = Modifier
                .padding(start = Padding.Padding15)
                .width(Dimen.Dimen50)
                .height(Dimen.Dimen50)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier
                .padding(
                    top = Padding.Padding10,
                    bottom = Padding.Padding15,
                    start = Padding.Padding10,
                    end = Padding.Padding10
                )
                .weight(1F)
        ) {
            Text(
                model.name.orEmpty(),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Medium
                )
            )
            Text(
                stringResource(
                    MR.strings.private_area_dashboard_latest_workout_calories_burn,
                    model.calories ?: 0,
                    model.minutes ?: 0
                ),
                style = MaterialTheme.typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.outline
                ),
                modifier = Modifier.padding(top = Padding.Padding5)
            )
            LinearProgressIndicator(
                progress = 0.5F,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Padding.Padding15),
                color = MaterialTheme.colorScheme.tertiary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }
        Image(
            painter = painterResource("ic_private_area_workout_btn.xml"),
            contentDescription = null,
            modifier = Modifier
                .padding(end = Padding.Padding15)
                .width(Dimen.Dimen24)
                .height(Dimen.Dimen24)
                .clip(CircleShape)
        )
    }
}
