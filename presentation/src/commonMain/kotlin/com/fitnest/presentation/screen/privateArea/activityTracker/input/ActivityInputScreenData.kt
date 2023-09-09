package com.fitnest.presentation.screen.privateArea.activityTracker.input

import com.fitnest.domain.enum.ActivityType

data class ActivityInputScreenData(
    val activityType: ActivityType = ActivityType.WATER,
    val value: Int = 0
)
