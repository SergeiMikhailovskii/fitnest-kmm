package com.fitnest.domain.internal.date

import platform.Foundation.NSCalendar
import platform.Foundation.NSCalendarUnitDay
import platform.Foundation.NSCalendarUnitHour
import platform.Foundation.NSCalendarUnitMinute
import platform.Foundation.NSCalendarUnitMonth
import platform.Foundation.NSCalendarUnitSecond
import platform.Foundation.NSCalendarUnitYear
import platform.Foundation.NSDate
import platform.Foundation.NSDateComponents
import platform.Foundation.create
import platform.Foundation.timeIntervalSince1970

actual typealias Date = NSDate

actual val Date.timeInMs: Long
    get() = (this.timeIntervalSince1970 * 1000).toLong()

actual fun Date.setTimeInMs(timeInMs: Long): Date {
    return Date.create(timeIntervalSince1970 = timeInMs.toDouble() / 1000)
}

actual val Date.year: Int
    get() {
        val calendar = NSCalendar.currentCalendar
        val components = calendar.components(NSCalendarUnitYear, fromDate = this)
        return components.year.toInt()
    }

actual val Date.month: Int
    get() {
        val calendar = NSCalendar.currentCalendar
        val components = calendar.components(NSCalendarUnitMonth, fromDate = this)
        return components.month.toInt()
    }
actual val Date.day: Int
    get() {
        val calendar = NSCalendar.currentCalendar
        val components = calendar.components(NSCalendarUnitDay, fromDate = this)
        return components.day.toInt()
    }
actual val Date.hour: Int
    get() {
        val calendar = NSCalendar.currentCalendar
        val components = calendar.components(NSCalendarUnitHour, fromDate = this)
        return components.hour.toInt()
    }
actual val Date.minute: Int
    get() {
        val calendar = NSCalendar.currentCalendar
        val components = calendar.components(NSCalendarUnitMinute, fromDate = this)
        return components.minute.toInt()
    }
actual val Date.second: Int
    get() {
        val calendar = NSCalendar.currentCalendar
        val components = calendar.components(NSCalendarUnitSecond, fromDate = this)
        return components.second.toInt()
    }

actual fun Date.subtractMonth(value: Long): Date {
    val calendar = NSCalendar.currentCalendar
    val offsetComponents = NSDateComponents()
    offsetComponents.calendar = calendar
    offsetComponents.setMonth(-value)
    val date = calendar.dateByAddingComponents(comps = offsetComponents, this, 0u)
    return date ?: throw RuntimeException("[Date] cannot subtract month")
}
