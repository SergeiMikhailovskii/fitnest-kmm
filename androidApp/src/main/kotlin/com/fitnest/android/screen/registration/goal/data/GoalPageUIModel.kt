package com.fitnest.android.screen.registration.goal.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class GoalPageUIModel(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
)
