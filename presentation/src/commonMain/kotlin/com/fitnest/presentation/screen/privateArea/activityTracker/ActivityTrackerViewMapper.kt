package com.fitnest.presentation.screen.privateArea.activityTracker

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import com.fitnest.domain.entity.request.AddActivityRequest
import com.fitnest.domain.entity.request.DeleteActivityRequest
import com.fitnest.domain.entity.response.ActivityTrackerPageResponse
import com.fitnest.domain.enum.ActivityType
import com.fitnest.domain.extension.orZero
import com.fitnest.presentation.MR
import com.fitnest.presentation.mapper.DateMapper
import com.fitnest.presentation.screen.privateArea.activityTracker.data.ActivityTrackerScreenData
import com.fitnest.presentation.screen.privateArea.activityTracker.input.ActivityInputScreenData
import com.fitnest.presentation.screen.privateArea.home.data.HomeScreenData
import dev.icerock.moko.resources.desc.ResourceFormatted
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.datetime.DayOfWeek

class ActivityTrackerViewMapper(
    private val dateMapper: DateMapper
) {

    @Composable
    fun mapActivityProgressesColors(progresses: ImmutableList<ActivityTrackerScreenData.Progress>?) =
        progresses?.mapIndexed { index, it ->
            it.copy(
                color = if (index % 2 == 0) {
                    MaterialTheme.colorScheme.primary.toArgb()
                } else {
                    MaterialTheme.colorScheme.tertiary.toArgb()
                }
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
                        waterIntake = StringDesc.ResourceFormatted(
                            MR.strings.private_area_activity_tracker_screen_today_target_litres,
                            (it.waterIntake ?: 0).toDouble() / 1000
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
                title = mapActivityTypeToTitle(it.type ?: ActivityType.WATER, it.amount.orZero),
                description = dateMapper.mapLocalDateTimeToString(it.time, "dd MMMM, hh:mm"),
                icon = mapActivityTypeToIcon(it.type)
            )
        }

    private fun mapProgressesResponseModelToUIModel(progresses: List<ActivityTrackerPageResponse.Progress>?) =
        progresses?.map {
            ActivityTrackerScreenData.Progress(
                day = requireNotNull(it.date?.dayOfWeek?.let(::mapDayOfWeekToShortName)),
                progress = (it.total?.toFloat() ?: 0F) / 10_000
            )
        }

    private fun mapDayOfWeekToShortName(dayOfWeek: DayOfWeek) =
        listOf(
            MR.strings.day_names_short_0,
            MR.strings.day_names_short_1,
            MR.strings.day_names_short_2,
            MR.strings.day_names_short_3,
            MR.strings.day_names_short_4,
            MR.strings.day_names_short_5,
            MR.strings.day_names_short_6
        )[dayOfWeek.ordinal]

    private fun mapActivityTypeToTitle(type: ActivityType, amount: Int) =
        if (type == ActivityType.WATER) {
            StringDesc.ResourceFormatted(
                MR.strings.private_area_activity_tracker_screen_latest_activity_water_title,
                amount
            )
        } else {
            StringDesc.ResourceFormatted(
                MR.strings.private_area_activity_tracker_screen_latest_activity_steps_title,
                amount
            )
        }

    private fun mapActivityTypeToIcon(type: ActivityType?) =
        if (type == ActivityType.WATER) {
            "ic_private_area_activity_water.xml"
        } else {
            "ic_private_area_notification_workout.xml"
        }
}
