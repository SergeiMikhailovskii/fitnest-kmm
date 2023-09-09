package com.fitnest.presentation.mapper

import com.fitnest.domain.extension.dateToString
import com.fitnest.domain.internal.date.Date
import com.fitnest.domain.internal.date.setTimeInMs
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

class DateMapper {

    fun mapLocalDateTimeToString(dateTime: LocalDateTime?, pattern: String): String = run {
        val millis =
            dateTime?.toInstant(TimeZone.currentSystemDefault())?.toEpochMilliseconds() ?: 0
        val date = Date().apply { setTimeInMs(millis) }
        date.dateToString(pattern)
    }
}
