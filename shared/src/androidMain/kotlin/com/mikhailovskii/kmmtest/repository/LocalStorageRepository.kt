package com.mikhailovskii.kmmtest.repository

import org.kodein.di.DI

actual class LocalStorageRepository actual constructor(actual val di: DI) {

    actual fun <T> saveValue(key: String, value: T?) {
        println("Saved with Android: key=$key, value=$value")
    }

}