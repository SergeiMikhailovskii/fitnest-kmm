package com.fitnest.android.screen.private_area.home

import android.content.Context
import com.fitnest.android.R
import com.fitnest.android.screen.private_area.home.data.HomeScreenData
import com.fitnest.domain.entity.response.DashboardResponse
import com.fitnest.domain.enum.BMIType

class HomeViewMapper(
    private val context: Context
) {

    internal fun mapDashboardResponseToScreenData(response: DashboardResponse): HomeScreenData {
        val headerResponse = response.widgets?.headerWidget
        val bmiResponse = response.widgets?.bmiWidget
        val todayTargetResponse = response.widgets?.todayTargetWidget
        val activityStatusResponse = response.widgets?.activityStatusWidget
        val latestWorkoutResponse = response.widgets?.latestWorkoutWidget

        val headerWidget = mapHeaderWidget(headerResponse)
        val bmiWidget = mapBMIWidget(bmiResponse)
        val todayTargetWidget = mapTodayTargetWidget(todayTargetResponse)
        val activityStatusWidget = mapActivityStatusWidget(activityStatusResponse)
        val latestWorkoutWidget = mapLatestWorkoutWidget(latestWorkoutResponse)

        return HomeScreenData(
            headerWidget = headerWidget,
            bmiWidget = bmiWidget,
            todayTargetWidget = todayTargetWidget,
            activityStatusWidget = activityStatusWidget,
            latestWorkoutWidget = latestWorkoutWidget
        )
    }

    private fun mapHeaderWidget(headerResponse: DashboardResponse.HeaderWidget?) =
        if (headerResponse != null) {
            HomeScreenData.HeaderWidget(
                name = headerResponse.name,
                hasNotifications = (headerResponse.notifications ?: 0) > 0
            )
        } else {
            null
        }

    private fun mapBMIWidget(bmiResponse: DashboardResponse.BMIWidget?): HomeScreenData.BMIWidget? {
        return if (bmiResponse != null) {
            val bmiResult = when (bmiResponse.result) {
                BMIType.UNDERWEIGHT -> R.string.private_area_dashboard_bmi_underweight
                BMIType.NORMAL_WEIGHT -> R.string.private_area_dashboard_bmi_normal_weight
                BMIType.OVERWEIGHT -> R.string.private_area_dashboard_bmi_overweight
                BMIType.OBESITY -> R.string.private_area_dashboard_bmi_obesity
                null -> R.string.error_unknown
            }
            HomeScreenData.BMIWidget(
                index = bmiResponse.index,
                result = bmiResult
            )
        } else {
            null
        }
    }

    private fun mapTodayTargetWidget(todayTargetResponse: DashboardResponse.TodayTargetWidget?) =
        if (todayTargetResponse != null) {
            HomeScreenData.TodayTargetWidget()
        } else {
            null
        }

    private fun mapActivityStatusWidget(activityStatusResponse: DashboardResponse.ActivityStatusWidget?) =
        if (activityStatusResponse != null) {
            HomeScreenData.ActivityStatusWidget(
                heartRateSubWidget = mapHeartRateSubWidget(activityStatusResponse.heartRate),
                waterIntakeSubWidget = mapWaterIntakeSubWidget(activityStatusResponse.waterIntake),
                sleepSubWidget = mapSleepSubWidget(activityStatusResponse.sleep),
                caloriesSubWidget = mapCaloriesSubWidget(activityStatusResponse.calories)
            )
        } else {
            null
        }

    private fun mapLatestWorkoutWidget(latestWorkoutResponse: DashboardResponse.LatestWorkoutWidget?) =
        if (latestWorkoutResponse != null) {
            HomeScreenData.LatestWorkoutWidget(
                workouts = latestWorkoutResponse.workouts?.map {
                    HomeScreenData.Workout(
                        name = it.name,
                        calories = it.calories,
                        minutes = it.minutes,
                        progress = it.progress,
                        image = it.image
                    )
                }
            )
        } else {
            null
        }

    private fun mapHeartRateSubWidget(heartRateResponse: DashboardResponse.HeartRateSubWidget?) =
        if (heartRateResponse != null) {
            HomeScreenData.HeartRateSubWidget(
                rate = heartRateResponse.rate,
                date = heartRateResponse.date
            )
        } else {
            null
        }

    private fun mapWaterIntakeSubWidget(waterIntakeResponse: DashboardResponse.WaterIntakeSubWidget?) =
        if (waterIntakeResponse != null) {
            HomeScreenData.WaterIntakeSubWidget(
                amount = ((waterIntakeResponse.amount ?: 0) / 1000).toDouble(),
                progress = waterIntakeResponse.progress,
                intakes = waterIntakeResponse.intakes?.groupBy {
                    it.timeDiapason?.hour
                }?.map {
                    val hoursFrom = it.key
                    val hoursTo = it.key?.plus(1)

                    val hoursFromStr = if (isBeforeMidDay(hoursFrom)) context.getString(
                        R.string.private_area_dashboard_time_am,
                        hoursFrom
                    ) else context.getString(R.string.private_area_dashboard_time_pm, hoursFrom)

                    val hoursToStr = if (isBeforeMidDay(hoursTo)) context.getString(
                        R.string.private_area_dashboard_time_am,
                        hoursTo
                    ) else context.getString(R.string.private_area_dashboard_time_pm, hoursTo)

                    val timeDiapason = "$hoursFromStr - $hoursToStr"
                    val amountByHour = it.value.sumOf { it.amountInMillis ?: 0 }
                    HomeScreenData.WaterIntake(
                        timeDiapason = timeDiapason,
                        amountInMillis = amountByHour
                    )
                }?.reversed()
            )
        } else {
            null
        }

    private fun mapSleepSubWidget(sleepResponse: DashboardResponse.SleepSubWidget?) =
        if (sleepResponse != null) {
            HomeScreenData.SleepSubWidget(
                hours = sleepResponse.hours,
                minutes = sleepResponse.minutes
            )
        } else {
            null
        }

    private fun mapCaloriesSubWidget(caloriesResponse: DashboardResponse.CaloriesSubWidget?) =
        if (caloriesResponse != null) {
            HomeScreenData.CaloriesSubWidget(
                consumed = caloriesResponse.consumed,
                left = caloriesResponse.left
            )
        } else {
            null
        }

    private fun isBeforeMidDay(hours: Int?) = hours in 0..12

}