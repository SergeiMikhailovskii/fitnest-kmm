package com.mikhailovskii.kmmtest.repository

actual class LocalStorageRepository actual constructor() {

    actual fun saveValue(key: String, value: Any?) {
        println("Saved with Android: key=$key, value=$value")
    }

}