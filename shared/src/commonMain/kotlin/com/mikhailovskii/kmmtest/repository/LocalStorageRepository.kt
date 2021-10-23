package com.mikhailovskii.kmmtest.repository

expect class LocalStorageRepository() {

    fun <T> saveValue(key: String, value: T?)

}