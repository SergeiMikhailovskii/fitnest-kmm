package com.mikhailovskii.kmmtest.repository

actual class LocalStorageRepository actual constructor() {

    actual fun <T> saveValue(key: String, value: T?) {
        println("Saved with Android: key=$key, value=$value")
    }

}