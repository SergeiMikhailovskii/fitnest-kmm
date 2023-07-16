package com.fitnest.domain.internal.date

import java.util.Calendar


actual typealias Date = java.util.Date

actual val Date.timeInMs: Long
    get() = this.time

actual val Date.year: Int
    get() {
        val calendar = Calendar.getInstance()
        calendar.time = this
        return calendar.get(Calendar.YEAR)
    }

actual val Date.month: Int
    get() {
        val calendar = Calendar.getInstance()
        calendar.time = this
        return calendar.get(Calendar.MONTH)
    }
actual val Date.day: Int
    get() {
        val calendar = Calendar.getInstance()
        calendar.time = this
        return calendar.get(Calendar.DAY_OF_MONTH)
    }
actual val Date.hour: Int
    get() {
        val calendar = Calendar.getInstance()
        calendar.time = this
        return calendar.get(Calendar.HOUR_OF_DAY)
    }
actual val Date.minute: Int
    get() {
        val calendar = Calendar.getInstance()
        calendar.time = this
        return calendar.get(Calendar.MINUTE)
    }
actual val Date.second: Int
    get() {
        val calendar = Calendar.getInstance()
        calendar.time = this
        return calendar.get(Calendar.SECOND)
    }

actual fun Date.setTimeInMs(timeInMs: Long) : Date {
    this.time = timeInMs
    return this
}

actual fun Date.subtractMonth(value: Long): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.MONTH, (-value).toInt())
    return calendar.time
}
