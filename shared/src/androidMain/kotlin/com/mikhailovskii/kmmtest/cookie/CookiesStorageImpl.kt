package com.mikhailovskii.kmmtest.cookie

import android.content.Context
import com.mikhailovskii.kmmtest.enum.CookieType
import io.ktor.http.*
import org.kodein.di.DI
import org.kodein.di.instance

actual class CookiesStorageImpl actual constructor(actual val di: DI) {

    private val context: Context by di.instance()

    private val preferences by lazy {
        context.getSharedPreferences("fitnestapp", Context.MODE_PRIVATE)
    }

    actual fun addCookie(requestUrl: Url, cookie: Cookie) {
        println("Set cookie name=${cookie.name}, value=${cookie.value}")
        val editor = preferences.edit()
        if (CookieType.values().any { it.value == cookie.name }) {
            editor.putString(cookie.name, cookie.value)
        }
        editor.apply()
    }

    actual fun getCookies() = mutableListOf<Cookie>().apply {
        CookieType.values().forEach { cookie ->
            preferences.getString(cookie.value, null)?.let {
                println("Loaded: ${cookie.value}=${it}")
                add(Cookie(cookie.value, it))
            }
        }
    }

}