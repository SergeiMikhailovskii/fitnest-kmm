package com.fitnest.repository

import org.kodein.di.DI

expect class LocalStorageRepository(di: DI) {

    val di: DI

    fun <T> saveValue(key: String, value: T?)

    inline fun <reified T> getValue(key: String, defaultValue: T? = null): T?
}
