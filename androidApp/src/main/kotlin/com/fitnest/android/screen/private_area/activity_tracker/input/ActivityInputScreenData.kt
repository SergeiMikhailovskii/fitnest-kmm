package com.fitnest.android.screen.private_area.activity_tracker.input

import com.fitnest.domain.enum.ActivityType

internal data class ActivityInputScreenData(
    val activityType: ActivityType = ActivityType.WATER,
    val value: Int = 0
)
