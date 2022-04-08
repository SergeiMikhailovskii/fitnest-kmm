package com.fitnest.android.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String): String {
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    return sdf.format(this)
}