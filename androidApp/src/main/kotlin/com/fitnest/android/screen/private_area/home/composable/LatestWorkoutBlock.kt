package com.fitnest.android.screen.private_area.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.fitnest.android.R
import com.fitnest.android.screen.private_area.home.data.HomeScreenData
import com.fitnest.android.style.*

@Composable
fun LatestWorkoutBlock(latestWorkoutWidget: HomeScreenData.LatestWorkoutWidget) {
    Column(modifier = Modifier.padding(top = Padding.Padding30)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                stringResource(id = R.string.private_area_dashboard_latest_workout_title),
                style = PoppinsSemiBoldStyle16Black
            )
            Spacer(modifier = Modifier.weight(1F))
            Text(
                stringResource(id = R.string.private_area_dashboard_latest_workout_see_more),
                style = PoppinsMediumStyle12Gray2
            )
        }
        latestWorkoutWidget.workouts?.forEach {
            LatestWorkoutItem(
                modifier = Modifier
                    .padding(bottom = Padding.Padding15)
                    .shadow(Dimen.Dimen40)
                    .background(
                        color = WhiteColor,
                        shape = RoundedCornerShape(size = Dimen.Dimen16)
                    ),
                model = it
            )
        }
    }
}

@Composable
private fun LatestWorkoutItem(modifier: Modifier, model: HomeScreenData.Workout) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.ic_private_area_workout),
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
            Text(model.name.orEmpty(), style = PoppinsMediumStyle12Black)
            Text(
                stringResource(
                    id = R.string.private_area_dashboard_latest_workout_calories_burn,
                    model.calories ?: 0,
                    model.minutes ?: 0
                ),
                style = PoppinsNormalStyle10Gray2,
                modifier = Modifier.padding(top = Padding.Padding5)
            )
            LinearProgressIndicator(
                progress = 0.5F,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Padding.Padding15),
                color = SecondaryColor,
                backgroundColor = BorderColor
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_private_area_workout_btn),
            contentDescription = null,
            modifier = Modifier
                .padding(end = Padding.Padding15)
                .width(Dimen.Dimen24)
                .height(Dimen.Dimen24)
                .clip(CircleShape)
        )
    }
}
