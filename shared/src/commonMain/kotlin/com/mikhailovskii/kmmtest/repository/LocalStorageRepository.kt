package com.mikhailovskii.kmmtest.repository

expect class LocalStorageRepository() {

    fun saveValue(key: String, value: Any?)

}