package com.fitnest.android.screen.private_area.activity_tracker

import android.content.Context
import androidx.compose.ui.graphics.toArgb
import com.fitnest.android.R
import com.fitnest.android.screen.private_area.activity_tracker.data.ActivityTrackerScreenData
import com.fitnest.android.screen.private_area.home.data.HomeScreenData
import com.fitnest.android.style.BrandColor
import com.fitnest.android.style.SecondaryColor
import com.fitnest.domain.entity.response.ActivityTrackerPageResponse
import kotlinx.datetime.DayOfWeek

internal class ActivityTrackerViewMapper(
    private val context: Context
) {

    internal fun mapWidgetsToScreenData(widgets: ActivityTrackerPageResponse.ActivityTrackerWidgets?): ActivityTrackerScreenData =
        run {
            var screenData = ActivityTrackerScreenData()

            widgets?.latestActivityWidget?.let {
                val mappedActivities = mapActivitiesResponseModelToUIModel(it.activities)
                screenData = screenData.copy(
                    latestActivityWidget = ActivityTrackerScreenData.LatestActivityWidget(
                        activities = mappedActivities
                    )
                )
            }

            widgets?.activityProgressWidget?.let {
                val mappedProgresses = mapProgressesResponseModelToUIModel(it.progresses)
                screenData = screenData.copy(
                    activityProgressWidget = ActivityTrackerScreenData.ActivityProgressWidget(
                        progresses = mappedProgresses
                    )
                )
            }

            widgets?.activityProgressWidget?.let {
                val mappedProgresses = mapProgressesResponseModelToUIModel(it.progresses)
                screenData = screenData.copy(
                    activityProgressWidget = ActivityTrackerScreenData.ActivityProgressWidget(
                        progresses = mappedProgresses
                    )
                )
            }

            widgets?.todayTargetWidget?.let {
                screenData = screenData.copy(
                    todayTargetWidget = HomeScreenData.TodayTargetWidget(
                        waterIntake = it.waterIntake,
                        steps = it.steps
                    )
                )
            }

            screenData
        }

    private fun mapActivitiesResponseModelToUIModel(activities: List<ActivityTrackerPageResponse.Activity>?) =
        activities?.map {
            ActivityTrackerScreenData.Activity(
                amount = it.amount,
                time = it.time,
                type = it.type
            )
        }

    private fun mapProgressesResponseModelToUIModel(progresses: List<ActivityTrackerPageResponse.Progress>?) =
        progresses?.mapIndexed { index, it ->
            ActivityTrackerScreenData.Progress(
                day = it.date?.dayOfWeek?.let(::mapDayOfWeekToShortName) ?: "",
                progress = it.total?.toFloat() ?: 0F,
                color = if (index % 2 == 0) BrandColor.toArgb() else SecondaryColor.toArgb()
            )
        }

    private fun mapDayOfWeekToShortName(dayOfWeek: DayOfWeek) =
        context.resources.getStringArray(R.array.day_names_short)[dayOfWeek.ordinal]

}