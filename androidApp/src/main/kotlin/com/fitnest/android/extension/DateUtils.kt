package com.fitnest.android.extension

import java.util.*

internal fun isSameHour(firstDate: Long, secondDate: Long = Date().time): Boolean {
    val calendar1 = Calendar.getInstance().apply {
        timeInMillis = firstDate
    }
    val calendar2 = Calendar.getInstance().apply {
        timeInMillis = secondDate
    }

    return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
            && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
            && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)
            && calendar1.get(Calendar.HOUR_OF_DAY) == calendar2.get(Calendar.HOUR_OF_DAY)
}

internal fun getMinutesDiff(from: Long, to: Long = Date().time): Long = (to - from) / 1000 / 60

internal fun getHoursDiff(from: Long, to: Long = Date().time): Long = (to - from) / 1000 / 60