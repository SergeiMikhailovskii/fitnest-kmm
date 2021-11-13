package com.fitnest.repository

import org.kodein.di.DI

actual class LocalStorageRepository actual constructor(actual val di: DI) {

    actual fun <T> saveValue(key: String, value: T?) {
        println("Saved with IOS: key=$key, value=$value")
    }

    actual inline fun <reified T> getValue(key: String, defaultValue: T?): T? {
        // TODO: 23.10.21 implement
        return null
    }


}