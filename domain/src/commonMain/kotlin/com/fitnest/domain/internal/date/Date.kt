package com.fitnest.domain.internal.date


expect class Date constructor()
expect fun Date.setTimeInMs(timeInMs: Long): Date
expect val Date.timeInMs: Long
expect val Date.year: Int
expect val Date.month: Int
expect val Date.day: Int
expect val Date.hour: Int
expect val Date.minute: Int
expect val Date.second: Int
expect fun Date.subtractMonth(value: Long): Date
