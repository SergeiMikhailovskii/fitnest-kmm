package com.fitnest.domain.extension

val Int?.orZero
    get() = this ?: 0