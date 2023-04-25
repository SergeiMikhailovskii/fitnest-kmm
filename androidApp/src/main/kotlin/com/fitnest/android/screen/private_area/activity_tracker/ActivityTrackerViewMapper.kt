package com.fitnest.android.screen.private_area.activity_tracker

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import com.fitnest.android.R
import com.fitnest.android.mapper.DateMapper
import com.fitnest.android.screen.private_area.activity_tracker.data.ActivityTrackerScreenData
import com.fitnest.android.screen.private_area.activity_tracker.input.ActivityInputScreenData
import com.fitnest.android.screen.private_area.home.data.HomeScreenData
import com.fitnest.domain.entity.request.AddActivityRequest
import com.fitnest.domain.entity.request.DeleteActivityRequest
import com.fitnest.domain.entity.response.ActivityTrackerPageResponse
import com.fitnest.domain.enum.ActivityType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.datetime.DayOfWeek

internal class ActivityTrackerViewMapper(
    private val context: Context,
    private val dateMapper: DateMapper
) {

    @Composable
    fun mapActivityProgressesColors(progresses: ImmutableList<ActivityTrackerScreenData.Progress>?) =
        progresses?.mapIndexed { index, it ->
            it.copy(
                color = if (index % 2 == 0) MaterialTheme.colorScheme.primary.toArgb()
                else MaterialTheme.colorScheme.tertiary.toArgb()
            )
        }?.toImmutableList()

    fun mapWidgetsToScreenData(widgets: ActivityTrackerPageResponse.ActivityTrackerWidgets?): ActivityTrackerScreenData =
        run {
            var screenData = ActivityTrackerScreenData()

            widgets?.latestActivityWidget?.let {
                val mappedActivities = mapActivitiesResponseModelToUIModel(it.activities)
                screenData = screenData.copy(
                    latestActivityWidget = ActivityTrackerScreenData.LatestActivityWidget(
                        activities = mappedActivities?.toImmutableList()
                    )
                )
            }

            widgets?.activityProgressWidget?.let {
                val mappedProgresses = mapProgressesResponseModelToUIModel(it.progresses)
                screenData = screenData.copy(
                    activityProgressWidget = ActivityTrackerScreenData.ActivityProgressWidget(
                        progresses = mappedProgresses?.toImmutableList()
                    )
                )
            }

            widgets?.todayTargetWidget?.let {
                screenData = screenData.copy(
                    todayTargetWidget = HomeScreenData.TodayTargetWidget(
                        waterIntake = context.getString(
                            R.string.private_area_activity_tracker_screen_today_target_litres,
                            ((it.waterIntake ?: 0).toDouble() / 1000)
                        ),
                        steps = it.steps?.toString().orEmpty()
                    )
                )
            }

            screenData
        }

    fun mapActivityToDeleteActivityRequest(activity: ActivityTrackerScreenData.Activity) =
        DeleteActivityRequest(id = activity.id, type = activity.type)

    fun mapActivityInputToRequest(screenData: ActivityInputScreenData) =
        AddActivityRequest(amount = screenData.value, type = screenData.activityType)

    private fun mapActivitiesResponseModelToUIModel(activities: List<ActivityTrackerPageResponse.Activity>?) =
        activities?.map {
            ActivityTrackerScreenData.Activity(
                id = it.id ?: 0,
                type = it.type ?: ActivityType.WATER,
                title = mapActivityTypeToTitle(it.type ?: ActivityType.WATER, it.amount ?: 0),
                description = dateMapper.mapLocalDateTimeToString(it.time, "dd MMMM, hh:mm"),
                icon = mapActivityTypeToIcon(it.type)
            )
        }

    private fun mapProgressesResponseModelToUIModel(progresses: List<ActivityTrackerPageResponse.Progress>?) =
        progresses?.map {
            ActivityTrackerScreenData.Progress(
                day = it.date?.dayOfWeek?.let(::mapDayOfWeekToShortName).orEmpty(),
                progress = (it.total?.toFloat() ?: 0F) / 10_000,
            )
        }

    private fun mapDayOfWeekToShortName(dayOfWeek: DayOfWeek) =
        context.resources.getStringArray(R.array.day_names_short)[dayOfWeek.ordinal]

    private fun mapActivityTypeToTitle(type: ActivityType, amount: Int) =
        if (type == ActivityType.WATER) context.getString(
            R.string.private_area_activity_tracker_screen_latest_activity_water_title,
            amount
        ) else context.getString(
            R.string.private_area_activity_tracker_screen_latest_activity_steps_title,
            amount
        )

    private fun mapActivityTypeToIcon(type: ActivityType?) =
        if (type == ActivityType.WATER) R.drawable.ic_private_area_activity_water
        else R.drawable.ic_private_area_notification_workout
}
