package com.mikhailovskii.kmmtest.repository

actual class LocalStorageRepository actual constructor() {

    actual fun <T> saveValue(key: String, value: T?) {
        println("Saved with IOS: key=$key, value=$value")
    }

}