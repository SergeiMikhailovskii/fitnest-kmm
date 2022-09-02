package com.fitnest.android.screen.private_area.activity_tracker

import com.fitnest.android.screen.private_area.activity_tracker.data.ActivityTrackerScreenData
import com.fitnest.android.screen.private_area.home.data.HomeScreenData
import com.fitnest.domain.entity.response.ActivityTrackerPageResponse

internal class ActivityTrackerViewMapper {

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
        progresses?.map {
            ActivityTrackerScreenData.Progress(
                date = it.date,
                total = it.total
            )
        }

}