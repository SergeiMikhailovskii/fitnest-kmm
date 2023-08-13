package com.fitnest.presentation.screen.privateArea.home.data

import dev.icerock.moko.resources.StringResource
import kotlinx.collections.immutable.ImmutableList
import kotlinx.datetime.LocalDateTime

data class HomeScreenData(
    val headerWidget: HeaderWidget? = HeaderWidget(),
    val bmiWidget: BMIWidget? = BMIWidget(),
    val todayTargetWidget: TodayTargetWidget? = TodayTargetWidget(),
    val activityStatusWidget: ActivityStatusWidget? = null,
    val latestWorkoutWidget: LatestWorkoutWidget? = null
) {
    data class HeaderWidget(
        val name: String? = null,
        val hasNotifications: Boolean? = null
    )

    data class BMIWidget(
        val index: Double? = null,
        val result: StringResource? = null
    )

    data class TodayTargetWidget(
        val waterIntake: String = "",
        val steps: String = ""
    )

    data class ActivityStatusWidget(
        val heartRateSubWidget: HeartRateSubWidget? = null,
        val waterIntakeSubWidget: WaterIntakeSubWidget? = null,
        val sleepSubWidget: SleepSubWidget? = null,
        val caloriesSubWidget: CaloriesSubWidget? = null
    )

    data class HeartRateSubWidget(
        val rate: Int? = null,
        val date: LocalDateTime? = null
    )

    data class WaterIntakeSubWidget(
        val amount: Double? = null,
        val progress: Double? = null,
        val intakes: ImmutableList<WaterIntake>? = null
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
        val workouts: ImmutableList<Workout>? = null
    )

    data class Workout(
        val name: String? = null,
        val calories: Int? = null,
        val minutes: Int? = null,
        val progress: Double? = null,
        val image: String? = null
    )
}
