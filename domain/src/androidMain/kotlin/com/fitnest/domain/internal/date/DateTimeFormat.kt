package com.fitnest.domain.internal.date

import java.text.SimpleDateFormat
import java.util.Locale

actual class Locale actual constructor(
    private val language: String,
    private val country: String
) {
    actual constructor() : this(Locale.getDefault().language, Locale.getDefault().country)
    constructor(locale: Locale) : this(locale.language, locale.country)

    fun platformLocale(): Locale {
        return Locale(language, country)
    }

    actual companion object {
        actual fun getDefault(): com.fitnest.domain.internal.date.Locale {
            return Locale(Locale.getDefault())
        }
    }
}

actual class DateTimeFormat actual constructor(
    actual override var pattern: String,
    private val locale: com.fitnest.domain.internal.date.Locale
) : DateTimeFormatter {

    private val sdf: SimpleDateFormat by lazy { SimpleDateFormat(pattern, locale.platformLocale()) }

    actual constructor(pattern: String) : this(
        pattern = pattern,
        locale = com.fitnest.domain.internal.date.Locale.getDefault()
    )

    actual constructor() : this(DEFAULT_PATTERN)

    actual override fun dateToString(date: Date): String? {
        sdf.applyPattern(pattern)
        return sdf.format(date)
    }

    override var months: Array<String>
        get() = sdf.dateFormatSymbols.months
        set(value) {
            sdf.dateFormatSymbols = sdf.dateFormatSymbols.apply { months = value }
        }

    override var shortMonths: Array<String>
        get() = sdf.dateFormatSymbols.shortMonths
        set(value) {
            sdf.dateFormatSymbols = sdf.dateFormatSymbols.apply { shortMonths = value }
        }

    override var weekdays: Array<String>
        get() = sdf.dateFormatSymbols.weekdays
        set(value) {
            sdf.dateFormatSymbols = sdf.dateFormatSymbols.apply { weekdays = value }
        }

    actual override fun dateFromString(date: String): Date? {
        sdf.applyPattern(pattern)
        val dateObj = sdf.parse(date) ?: return null
        return Date(dateObj.time)
    }
}
