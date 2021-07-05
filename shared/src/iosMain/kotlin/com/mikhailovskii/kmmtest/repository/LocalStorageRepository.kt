package com.mikhailovskii.kmmtest.repository

actual class LocalStorageRepository actual constructor() {

    actual fun saveValue(key: String, value: Any?) {
        println("Saved with IOS: key=$key, value=$value")
    }

}