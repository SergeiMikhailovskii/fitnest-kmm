package com.fitnest.domain.extension

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.todayIn

val Long.isToday: Boolean
    get() {
        val dayStart = LocalDateTime(
            Clock.System.todayIn(TimeZone.currentSystemDefault()),
            LocalTime(0, 0)
        ).toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
        val dayEnd = LocalDateTime(
            Clock.System.todayIn(TimeZone.currentSystemDefault()).plus(1, DateTimeUnit.DAY),
            LocalTime(0, 0)
        ).toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
        return this in dayStart until dayEnd
    }