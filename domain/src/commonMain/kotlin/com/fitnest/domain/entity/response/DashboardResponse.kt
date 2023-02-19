package com.fitnest.domain.entity.response

import com.fitnest.domain.enum.BMIType
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DashboardResponse(
    val widgets: DashboardWidgets? = null
) {

    @Serializable
    data class DashboardWidgets(
        @SerialName("ACTIVITY_STATUS_WIDGET")
        val activityStatusWidget: ActivityStatusWidget? = null,

        @SerialName("BMI_WIDGET")
        val bmiWidget: BMIWidget? = null,

        @SerialName("HEADER_WIDGET")
        val headerWidget: HeaderWidget? = null,

        @SerialName("LATEST_WORKOUT_WIDGET")
        val latestWorkoutWidget: LatestWorkoutWidget? = null,

        @SerialName("TODAY_TARGET_WIDGET")
        val todayTargetWidget: TodayTargetWidget? = null
    )

    @Serializable
    data class ActivityStatusWidget(
        @SerialName("heart_rate")
        val heartRate: HeartRateSubWidget? = null,

        @SerialName("water_intake")
        val waterIntake: WaterIntakeSubWidget? = null,
        val sleep: SleepSubWidget? = null,
        val calories: CaloriesSubWidget? = null
    )

    @Serializable
    data class BMIWidget(
        val index: Double? = null,
        val result: BMIType? = null
    )

    @Serializable
    data class HeaderWidget(
        val name: String? = null,
        val notifications: Int? = null
    )

    @Serializable
    data class LatestWorkoutWidget(
        val workouts: List<Workout>? = null
    )

    @Serializable
    class TodayTargetWidget(
        @SerialName("water_intake")
        val waterIntake: Int? = null,
        val steps: Int? = null
    )

    @Serializable
    data class HeartRateSubWidget(
        val rate: Int? = null,
        val date: LocalDateTime? = null
    )

    @Serializable
    data class WaterIntakeSubWidget(
        val amount: Int? = null,
        val progress: Double? = null,
        val intakes: List<WaterIntake>? = null
    )

    @Serializable
    data class WaterIntake(
        @SerialName("time_diapason")
        val timeDiapason: LocalDateTime? = null,

        @SerialName("amount_in_millis")
        val amountInMillis: Int? = null
    )

    @Serializable
    data class SleepSubWidget(
        val hours: Int? = null,
        val minutes: Int? = null
    )

    @Serializable
    data class CaloriesSubWidget(
        val consumed: Int? = null,
        val left: Int? = null
    )

    @Serializable
    data class Workout(
        val name: String? = null,
        val calories: Int? = null,
        val minutes: Int? = null,
        val progress: Double? = null,
        val image: String? = null
    )
}
