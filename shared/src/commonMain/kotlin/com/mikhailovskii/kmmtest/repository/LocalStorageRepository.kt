package com.mikhailovskii.kmmtest.repository

import org.kodein.di.DI

expect class LocalStorageRepository(di: DI) {

    val di: DI

    fun <T> saveValue(key: String, value: T?)

}