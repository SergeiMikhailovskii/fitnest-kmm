package com.mikhailovskii.kmmtest.cookie

import android.content.Context
import io.ktor.http.*
import org.kodein.di.DI
import org.kodein.di.instance

actual class CookiesStorageImpl actual constructor(actual val di: DI) {

    private val cookieMap = mutableMapOf<String, String>()

    private val context: Context by di.instance()

    actual fun addCookie(requestUrl: Url, cookie: Cookie) {
        println("Set cookie name=${cookie.name}, value=${cookie.value}")
        cookieMap[cookie.name] = cookie.value
        context.getSharedPreferences("kmm_preferences", Context.MODE_PRIVATE)
        println()
    }

    actual fun getCookies() = mutableListOf<Cookie>().apply {
        cookieMap.forEach {
            this.add(Cookie(it.key, it.value))
        }
    }

}