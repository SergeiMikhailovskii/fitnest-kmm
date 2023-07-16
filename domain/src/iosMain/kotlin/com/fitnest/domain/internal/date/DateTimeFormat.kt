package com.fitnest.domain.internal.date

import platform.Foundation.*

actual class Locale actual constructor(
    private val language: String,
    private val country: String,
) {
    actual constructor() : this(getDefault().language, getDefault().country)
    constructor(locale: NSLocale) : this(locale.languageCode, locale.countryCode.orEmpty())

    fun platformLocale(): NSLocale {
        if (country.isEmpty()) {
            return NSLocale(language)
        }
        return NSLocale("${language}_${country.uppercase()}")
    }

    actual companion object {
        actual fun getDefault(): Locale {
            return Locale(NSLocale.currentLocale())
        }
    }
}

actual class DateTimeFormat actual constructor(
    actual override var pattern: String,
    private val locale: Locale,
) : DateTimeFormatter {

    private val formatter by lazy {
        NSDateFormatter().apply {
            locale = this@DateTimeFormat.locale.platformLocale()
        }
    }

    actual constructor(pattern: String) : this(pattern, Locale.getDefault())
    actual constructor() : this(DEFAULT_PATTERN)

    actual override fun dateToString(date: Date): String? {
        formatter.dateFormat = pattern
        return formatter.stringFromDate(date)
    }

    override var months: Array<String>
        get() = formatter.monthSymbols.map { it.toString() }.toTypedArray()
        set(value) {
            formatter.monthSymbols = value.toList()
        }
    override var shortMonths: Array<String>
        get() = formatter.shortMonthSymbols.map { it.toString() }.toTypedArray()
        set(value) {
            formatter.shortMonthSymbols = value.toList()
        }
    override var weekdays: Array<String>
        get() = formatter.weekdaySymbols.map { it.toString() }.toTypedArray()
        set(value) {
            formatter.weekdaySymbols = value.toList()
        }

    actual override fun dateFromString(date: String): Date? {
        formatter.dateFormat = pattern
        return formatter.dateFromString(date)
    }
}