package com.fitnest.android.screen.private_area.home.data

import androidx.annotation.DrawableRes

data class LatestWorkoutUIModel(
    @DrawableRes val icon: Int,
    val name: String,
    val calories: Int,
    val minutes: Int
)
