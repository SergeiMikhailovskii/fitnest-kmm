package com.fitnest.presentation.extension

import com.fitnest.domain.internal.date.Date
import com.fitnest.domain.internal.date.timeInMs

internal fun isSameHour(firstDate: Long, secondDate: Long = Date().timeInMs): Boolean {
    val millisInHour = 3_600_000
    return firstDate / millisInHour == secondDate / millisInHour
}

internal fun getMinutesDiff(from: Long, to: Long = Date().timeInMs): Long = (to - from) / 1000 / 60

internal fun getHoursDiff(from: Long, to: Long = Date().timeInMs): Long = (to - from) / 1000 / 60 / 60
