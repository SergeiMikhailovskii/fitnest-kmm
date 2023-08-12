package com.fitnest.domain.internal.date

interface DateTimeFormatter {
    var pattern: String
    var months: Array<String>
    var shortMonths: Array<String>
    var weekdays: Array<String>

    fun dateToString(date: Date): String?

    fun dateFromString(date: String): Date?
}

const val DEFAULT_PATTERN = "dd.MM.yyyy'T'HH:mm:ss"

expect class Locale constructor(
    language: String,
    country: String
) {

    constructor()

    companion object {
        fun getDefault(): Locale
    }
}

expect class DateTimeFormat constructor(pattern: String = DEFAULT_PATTERN, locale: Locale) : DateTimeFormatter {

    constructor(pattern: String)

    constructor()

    override var pattern: String

    override fun dateToString(date: Date): String?

    override fun dateFromString(date: String): Date?
}
