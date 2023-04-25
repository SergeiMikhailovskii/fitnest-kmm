package com.fitnest.android.mapper

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal class DateMapper {

    fun mapLocalDateTimeToString(dateTime: LocalDateTime?, pattern: String): String = run {
        val millis =
            dateTime?.toInstant(TimeZone.currentSystemDefault())?.toEpochMilliseconds() ?: 0
        val date = Date(millis)
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.format(date)
    }
}
