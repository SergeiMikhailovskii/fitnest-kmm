package com.fitnest.domain.extension

fun <T> MutableList<T>.move(fromIdx: Int, toIdx: Int) {
    if (toIdx > fromIdx) {
        for (i in fromIdx until toIdx) {
            this[i] = this[i + 1].also { this[i + 1] = this[i] }
        }
    } else {
        for (i in fromIdx downTo toIdx + 1) {
            this[i] = this[i - 1].also { this[i - 1] = this[i] }
        }
    }
}
