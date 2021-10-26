package com.mikhailovskii.kmmtest.repository

import android.content.Context
import org.kodein.di.DI
import org.kodein.di.instance

actual class LocalStorageRepository actual constructor(actual val di: DI) {

    private val context: Context by di.instance()

    val preferences by lazy {
        context.getSharedPreferences("fitnestapp", Context.MODE_PRIVATE)
    }

    actual fun <T> saveValue(key: String, value: T?) {
        println("Saved with Android: key=$key, value=$value")
        val editor = preferences.edit()
        when (value) {
            is String -> editor.putString(key, value)
        }
        editor.apply()
    }

    actual inline fun <reified T> getValue(key: String, defaultValue: T?): T? {
        return when (T::class) {
            String::class -> {
                preferences.getString(key, defaultValue as String?) as T?
            }
            else -> null
        }
    }

}