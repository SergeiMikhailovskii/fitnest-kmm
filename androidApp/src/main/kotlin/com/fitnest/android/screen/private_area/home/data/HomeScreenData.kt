package com.fitnest.android.screen.private_area.home.data

import androidx.annotation.StringRes
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

internal data class HomeScreenData(
    val headerWidget: HeaderWidget? = null,
    val bmiWidget: BMIWidget? = null,
    val todayTargetWidget: TodayTargetWidget? = null,
    val activityStatusWidget: ActivityStatusWidget? = null,
    val latestWorkoutWidget: LatestWorkoutWidget? = null
) {
    data class HeaderWidget(
        val name: String? = null,
        val hasNotifications: Boolean? = null
    )

    data class BMIWidget(
        val index: Double? = null,
        @StringRes val result: Int? = null
    )

    class TodayTargetWidget(
        val waterIntake: Int? = null,
        val steps: Int? = null
    )

    data class ActivityStatusWidget(
        val heartRateSubWidget: HeartRateSubWidget? = null,
        val waterIntakeSubWidget: WaterIntakeSubWidget? = null,
        val sleepSubWidget: SleepSubWidget? = null,
        val caloriesSubWidget: CaloriesSubWidget? = null,
    )

    data class HeartRateSubWidget(
        val rate: Int? = null,
        val date: LocalDateTime? = null
    )

    data class WaterIntakeSubWidget(
        val amount: Double? = null,
        val progress: Double? = null,
        val intakes: List<WaterIntake>? = null
    )

    data class WaterIntake(
        val timeDiapason: String? = null,
        val amountInMillis: Int? = null
    )

    data class SleepSubWidget(
        val hours: Int? = null,
        val minutes: Int? = null
    )

    data class CaloriesSubWidget(
        val consumed: Int? = null,
        val left: Int? = null
    )

    data class LatestWorkoutWidget(
        val workouts: List<Workout>? = null
    )

    data class Workout(
        val name: String? = null,
        val calories: Int? = null,
        val minutes: Int? = null,
        val progress: Double? = null,
        val image: String? = null
    )
}