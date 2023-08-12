package com.fitnest.domain.extension

import com.fitnest.domain.internal.date.Date
import com.fitnest.domain.internal.date.DateTimeFormat
import com.fitnest.domain.internal.date.Locale
import com.fitnest.domain.internal.date.setTimeInMs
import com.fitnest.domain.internal.date.timeInMs
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

fun Date.dateToString(pattern: String): String {
    val formatter = DateTimeFormat(pattern, Locale.getDefault())
    return formatter.dateToString(this).orEmpty()
}

fun Date.toLocalDate() = Instant.fromEpochMilliseconds(timeInMs).toLocalDateTime(TimeZone.currentSystemDefault()).date

fun LocalDate.toDate() = Date().setTimeInMs(
    LocalDateTime(this, LocalTime(0, 0)).toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
)
