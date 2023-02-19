package com.fitnest.android.extension.enum

import android.content.Context
import com.fitnest.android.R
import com.fitnest.domain.enum.ActivityType

val ActivityType.localizedNameId: Int
    get() = when (this) {
        ActivityType.STEPS -> R.string.private_area_activity_tracker_screen_activity_steps
        ActivityType.WATER -> R.string.private_area_activity_tracker_screen_activity_water
    }

fun ActivityType.Companion.localizedNames(context: Context) =
    ActivityType.values().map { it.localizedName(context) }

fun ActivityType.localizedName(context: Context) = context.getString(localizedNameId)

fun ActivityType.Companion.fromLocalizedName(activity: String, context: Context) =
    if (context.getString(ActivityType.WATER.localizedNameId) == activity) ActivityType.WATER
    else ActivityType.STEPS