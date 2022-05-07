package com.fitnest.android.screen.private_area.home.data

data class HomeScreenData(
    val userName: String,
    val bmiResultText: String,
    val bmiProgress: Double,
    val lastHeartRateValue: Int,
    val lastHeartRateTime: Int,
    val kcalValue: Int,
    val kcalLeftValue: Int,
    val kcalProgress: Float,
    val waterIntakeList: List<WaterIntakeUIModel>,
    val waterIntakeValue: Int,
    val latestWorkoutList: List<LatestWorkoutUIModel>
)
